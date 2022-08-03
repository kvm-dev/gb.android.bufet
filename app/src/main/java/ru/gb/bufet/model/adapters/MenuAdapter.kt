package ru.gb.bufet.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.ItemFoodBinding
import ru.gb.bufet.model.responseData.RestaurantFood
import ru.gb.bufet.viewModel.MainViewModel

class MenuAdapter(private val items: List<RestaurantFood>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        //я кст по рекомендации преподавателя поубирал отложенную инициализацию) но эт как говорится не обязательно, я просто к тому, либо нам в другие адаптеры ее вернуть либо тут убрать)
        private var foodItem: ConstraintLayout? = null
        private var foodPrice: Button? = null
        private var foodName: TextView? = null
        private var foodImage: ImageView? = null

        init {
            foodItem = binding.foodItem
            foodPrice = binding.addToCartButton
            foodName = binding.foodTitle
            foodImage = binding.foodImage
        }

        @SuppressLint("SetTextI18n")
        fun bind(items: List<RestaurantFood>, position: Int) {
            //todo зачем тут контекст?
            //todo контекст здесь для того, чтоб получить доступ к активити, а именно для того, чтобы дергать навигационные методы, например открывать конкретное блюдо ну и использовать вьюмодел если требуется
            val context = itemView.context
            val activity: MainActivity = context as MainActivity
            //-----
            val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]

            items[position].name.let {
                foodName?.text = it
            }
            items[position].price.let {
                foodPrice?.text = " $it ${context.resources.getString(R.string.rub_value)}"
            }

            ContextCompat.getDrawable(activity, R.drawable.image_not_found)
                ?.let { ContextCompat.getDrawable(activity, R.drawable.image_not_found)?.let { it1 ->
                    Picasso.get().load(items[position].pictures).error(it).placeholder(it1).into(foodImage)
                } }

            foodItem?.setOnClickListener {
                //todo open food fragment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            bind(items, position)
        }
    }

    override fun getItemCount() = items.size
}