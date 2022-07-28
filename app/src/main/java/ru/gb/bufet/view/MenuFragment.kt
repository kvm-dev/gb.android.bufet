package ru.gb.bufet.view

import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.viewModel.FoodViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {

    private lateinit var adapter: MenuAdapter
    override fun init() {
        loadRestaurantMenu()
    }


    private fun loadRestaurantMenu() {


        val verticalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.fragmentMenuMenuRecycler.layoutManager = verticalLayoutManager
        foodModel = FoodViewModel()
        foodModel.foodListResponse.observe(
            this
        ) { foods ->
            adapter = MenuAdapter(foods)
            binding.fragmentMenuMenuRecycler.adapter = adapter
        }
//    val testFilterList = resources.getStringArray(R.array.main_display_title_tabs)
//        loadFilterNavigation(testFilterList)
    }
}

