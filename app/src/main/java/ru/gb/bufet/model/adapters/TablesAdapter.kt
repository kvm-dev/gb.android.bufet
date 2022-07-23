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
RecyclerView.Adapter<TablesAdapter.MyViewHolder>() {
    class MyViewHolder(binding: ItemTableBinding) : RecyclerView.ViewHolder(binding.root) {
        private var tabletItem: CardView? = null
        var tableNumber: TextView? = null
        var guestCounter: TextView? = null
        var availableNow: TextView? = null

        init {
            tabletItem = binding.itemTableCard
            tableNumber = binding.tableNumber
            guestCounter = binding.guestCounter
            availableNow = binding.availableNow
        }

        @SuppressLint("SetTextI18n")
        fun bind(items: List<RestaurantTable>, id: Int) {
            val context = itemView.context
            val activity : MainActivity = context as MainActivity
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]

            items[id].id.let{
                tableNumber?.text = it?.toString()
            }
            items[id].guestsCount.let{
                guestCounter?.text = "${context.resources.getString(R.string.guest_counter_text)} ${it?.toString()}"
            }
            items[id].availability.let {
            }
            items[id].availability.let{
                if (it != 0){
                    availableNow?.text = context.resources.getString(R.string.table_available)
                }
                else{
                    availableNow?.text = context.resources.getString(R.string.table_available)
                }
            }
            tabletItem?.setOnClickListener {
             (activity).goToReserve()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            bind(items, position)
        }
    }

    override fun getItemCount() = items.size
}