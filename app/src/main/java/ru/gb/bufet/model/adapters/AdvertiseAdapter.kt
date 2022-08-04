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
import ru.gb.bufet.databinding.ItemAdvertiseBinding
import ru.gb.bufet.model.responseData.AdvertiseBanners
import ru.gb.bufet.viewModel.MainViewModel

class AdvertiseAdapter (private val items: ArrayList<AdvertiseBanners>) :
    RecyclerView.Adapter<AdvertiseAdapter.AdvertiseViewHolder>() {
    class AdvertiseViewHolder(val binding: ItemAdvertiseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: List<AdvertiseBanners>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
            //check images
            ContextCompat.getDrawable(activity, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(activity, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(items[id].image).error(it).placeholder(it1).into(binding.itemAdvertiseImage)
                } }
            //check texts
            items[id].title.let {
                binding.itemAdvertiseTitle.text = it
            }
            items[id].description.let {
                binding.itemAdvertiseDescription.text = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertiseViewHolder {
        val binding = ItemAdvertiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdvertiseViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdvertiseViewHolder, position: Int) {
        holder.bind(items, position)
    }

    override fun getItemCount() = items.size
}
