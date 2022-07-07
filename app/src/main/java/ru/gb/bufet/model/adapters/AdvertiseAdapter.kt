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
import ru.gb.bufet.viewModel.MainViewModel

class AdvertiseAdapter (private val items: ArrayList<ResponseData.AdvertiseBanners>) :
    RecyclerView.Adapter<AdvertiseAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var advItem: CardView? = null
        private var advImage: ImageView? = null
        var advTitle: TextView? = null
        var advText: TextView? = null

        init {
            advItem = itemView.findViewById(R.id.advertiseBanner)
            advImage = itemView.findViewById(R.id.advertiseImage)
            advTitle = itemView.findViewById(R.id.advertiseTitle)
            advText = itemView.findViewById(R.id.advertiseDescription)
        }

        fun bind(items: List<ResponseData.AdvertiseBanners>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
            //check images
            ContextCompat.getDrawable(activity, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(activity, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(items[id].image).error(it).placeholder(it1).into(advImage)
                } }
            //check texts
            if(items[id].title!=null){
                advTitle?.text = items[id].title
            }
            if(items[id].description!=null){
                advText?.text = items[id].description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_advertise, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items, position)
    }

    override fun getItemCount() = items.size
}
