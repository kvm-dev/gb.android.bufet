package ru.gb.bufet.model.adapters

import android.annotation.SuppressLint
import android.util.Log
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
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.utils.ServerUtils
import ru.gb.bufet.viewModel.MainViewModel

class RestaurantsAdapter (private val items: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    class RestaurantsViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: List<Restaurant>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
            //check images
            ContextCompat.getDrawable(activity, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(activity, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(items[id].headerImage).error(it).placeholder(it1).into(binding.restaurantHeader)
                } }
            items[id].restaurantTables.let {
                binding.tableCounter.text = it?.size.toString()
            }
            if(items[id].work_start!=null && items[id].work_end!= null){
                binding.workTimeData.text = ServerUtils().checkWorkTimeFromTimeStamp(items[id].work_start!!, items[id].work_end!!)
            }
            binding.itemRestaurantCard.setOnClickListener {
                viewModel.currentRestaurant.value = null
                viewModel.currentRestaurant.value = items[id]
                activity.goToRestaurant()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantsViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
          with(holder){
              with(items[position]){
                  binding.restaurantTitle.text = name
              }
              bind(items, position)
          }
    }

    override fun getItemCount() = items.size
}