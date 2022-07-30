package ru.gb.bufet.view

import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.responseData.Restaurant

class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {
    override fun init() {

        val restModel = getCurrentRestOrDefault()
        bindingHeader(restModel)
        loadRestaurantMenu(restModel)
    }


    private fun getCurrentRestOrDefault(): Restaurant {
        val restaurantModel = viewModel.currentRestaurant.value
        return restaurantModel!!
        //todo передать бы сюда какой-то дефолтный рест, если карент null
    }

    private fun bindingHeader(restModel: Restaurant) {
        binding.fragmentMenuBackButton.setOnClickListener {
            (activity?.onBackPressed())
        }
        binding.fragmentMenuTitle.text = restModel.name
    }

    private fun loadRestaurantMenu(restModel: Restaurant) {
        binding.fragmentMenuMenuRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = restModel.restaurantFood?.let { MenuAdapter(it) }
        }
    }
}
