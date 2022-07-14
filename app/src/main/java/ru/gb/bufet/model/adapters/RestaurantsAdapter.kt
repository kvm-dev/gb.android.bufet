package ru.gb.bufet.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.ItemRestaurantBinding
import ru.gb.bufet.model.responseData.ResponseData
import ru.gb.bufet.model.utils.ServerUtils
import ru.gb.bufet.viewModel.MainViewModel

class RestaurantsAdapter (private val items: ArrayList<ResponseData.Restaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>() {
    class MyViewHolder(binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        private var restaurantItem: CardView? = null
        private var restaurantImage: ImageView? = null
        var restaurantTables: TextView? = null
        var restaurantWorkTime: TextView? = null
        var restaurantName: TextView? = null

        init {
            restaurantItem = binding.itemRestaurantCard
            restaurantImage = binding.restaurantHeader
            restaurantTables = binding.tableCounter
            restaurantWorkTime = binding.workTimeData
            restaurantName = binding.restaurantTitle
        }

        fun bind(items: List<ResponseData.Restaurant>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
            //check images
            ContextCompat.getDrawable(activity, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(activity, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(items[id].headerImage).error(it).placeholder(it1).into(restaurantImage)
                } }

            items[id].restaurantTables.let {
                restaurantTables?.text = it?.size.toString()
            }

            if(items[id].work_start!=null && items[id].work_end!= null){
                restaurantWorkTime?.text = ServerUtils().checkWorkTimeFromTimeStamp(context, items[id].work_start!!, items[id].work_end!!)
            }
//            else{
//                restaurantWorkTime?.text = context.resources.getString(R.string.restaurant_not_open)
//            }
            restaurantItem?.setOnClickListener {
                viewModel.currentRestaurant.value = null
                viewModel.currentRestaurant.value = items[id]
                activity.goToRestaurant()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          with(holder){
              with(items[position]){
                  restaurantName?.text = name
              }
              bind(items, position)
          }
    }

    override fun getItemCount() = items.size
}