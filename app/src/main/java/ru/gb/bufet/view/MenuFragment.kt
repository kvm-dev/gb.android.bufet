package ru.gb.bufet.view

import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.model.adapters.RestaurantsAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.viewModel.FoodViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {

    //  private lateinit var adapter: MenuAdapter // вот тут на мой взгляд опасненько, т.к. мы не можем гарантировать, что мы адаптер чем-нить инициализируем, т.к. у рестика в какой-то момент времени может не быть меню- ну мало ли тупанули админы или еще чего.
    override fun init() {
        loadRestaurantMenu()
    }


    private fun loadRestaurantMenu() {
        //да можно примерно так как ты и описываешь, если ты хочешь свежие данные запросить и их сюда перекинуть, но т.к. данные у нас уже есть на этом этапе, я бы просто их и передал в адаптер.
        if (viewModel.currentRestaurant.value != null) {
            binding.fragmentMenuMenuRecycler.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                adapter = viewModel.currentRestaurant.value?.restaurantFood?.let { MenuAdapter(it) }

//        val verticalLayoutManager = LinearLayoutManager(
//            requireContext(),
//            LinearLayoutManager.VERTICAL,
//            false
//        )
//        binding.fragmentMenuMenuRecycler.layoutManager = verticalLayoutManager
//        foodModel = FoodViewModel()
//        foodModel.foodListResponse.observe(
//            this
//        ) { foods ->
//            adapter = MenuAdapter(foods)
//            binding.fragmentMenuMenuRecycler.adapter = adapter
//        }
//    val testFilterList = resources.getStringArray(R.array.main_display_title_tabs)
//        loadFilterNavigation(testFilterList)
            }
        }
    }
}

