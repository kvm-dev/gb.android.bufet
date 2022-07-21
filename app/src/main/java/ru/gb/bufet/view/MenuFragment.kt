package ru.gb.bufet.view

import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.model.data.BaseFragment

class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {

    private lateinit var adapter: MenuAdapter
    override fun init() {

        loadRestaurantMenu()
    }


    private fun loadRestaurantMenu() {

        if (viewModel.foodListResponse.value != null) {
//            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value

            val verticalLayoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            binding.fragmentMenuMenuRecycler.layoutManager = verticalLayoutManager

            adapter = viewModel.foodListResponse.value?.let {
                MenuAdapter(it)
            }!!
            binding.fragmentMenuMenuRecycler.adapter = adapter
            val testFilterList = resources.getStringArray(R.array.main_display_title_tabs)
//        loadFilterNavigation(testFilterList)
        }
    }
}

