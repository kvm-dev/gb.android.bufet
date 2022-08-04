package ru.gb.bufet.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.ItemTableBinding
import ru.gb.bufet.model.responseData.RestaurantTable
import ru.gb.bufet.viewModel.MainViewModel

class TablesAdapter (private val items: List<RestaurantTable>) :
RecyclerView.Adapter<TablesAdapter.TablesViewHolder>() {
    class TablesViewHolder(val binding: ItemTableBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(items: List<RestaurantTable>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]

            items[id].tableNumber.let{
                binding.itemTableNumber.text = it?.toString()
            }
            items[id].guestsCount.let{
                binding.itemTableGuestCounter.text = "${context.resources.getString(R.string.guest_counter_text)} ${it?.toString()}"
            }
            items[id].availability.let {
            }
            items[id].availability.let{
                if (it != 0){
                    binding.itemTableAvailableNow.text = context.resources.getString(R.string.table_available)
                }
                else{
                    binding.itemTableAvailableNow.text = context.resources.getString(R.string.table_available)
                }
            }
            binding.itemTableCard.setOnClickListener {
             (activity).goToReserve()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TablesViewHolder {
        val binding = ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TablesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TablesViewHolder, position: Int) {
        with(holder){
            bind(items, position)
        }
    }

    override fun getItemCount() = items.size
}