package ru.gb.bufet.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
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
import ru.gb.bufet.model.responseData.ResponseData
import ru.gb.bufet.model.utils.ServerUtils
import ru.gb.bufet.viewModel.MainViewModel

class RestaurantsAdapter (private val items: ArrayList<ResponseData.Restaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var restaurantItem: CardView? = null
        private var restaurantImage: ImageView? = null
        var restaurantTables: TextView? = null
        var restaurantWorkTime: TextView? = null
        var restaurantName: TextView? = null

        init {
            restaurantItem = itemView.findViewById(R.id.itemRestaurantCard)
            restaurantImage = itemView.findViewById(R.id.restaurantHeader)
            restaurantTables = itemView.findViewById(R.id.tableCounter)
            restaurantWorkTime = itemView.findViewById(R.id.workTimeData)
            restaurantName = itemView.findViewById(R.id.restaurantTitle)
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
            if(items[id].restaurantTables?.size !=null){
                restaurantTables?.text = items[id].restaurantTables?.size.toString()
            }
            else{
                restaurantTables?.text = context.resources.getString(R.string.default_value)
            }
            if(items[id].work_start!=null && items[id].work_end!= null){
                restaurantWorkTime?.text = ServerUtils().checkWorkTimeFromTimeStamp(context, items[id].work_start!!, items[id].work_end!!)
            }
            else{
                restaurantWorkTime?.text = context.resources.getString(R.string.restaurant_not_open)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_restaurant, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items, position)
        holder.restaurantName?.text = "${items[position].name}"
    }

    override fun getItemCount() = items.size
}
