package ru.gb.bufet.view

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.responseData.RestaurantFood

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

        binding.fragmentMenuMenuRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.fragmentMenuMenuRecycler.adapter = restModel.restaurantFood?.let {
            MenuAdapter(it)
        }
        bindSearchView(restModel)
    }

    private fun bindSearchView(restModel: Restaurant) {
        binding.fragmentMenuSearchView.clearFocus()
        binding.fragmentMenuSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                searchByName(restModel, searchText)
                return true
            }
        })
    }

    private fun searchByName(restModel: Restaurant, searchText: String) {
        val filteredList = ArrayList<RestaurantFood>()
        for (restFood in restModel.restaurantFood!!) {
            if (restFood.name!!.lowercase().contains(searchText.lowercase()))
                filteredList.add(restFood)
        }

        val adapter = MenuAdapter(filteredList)
        adapter.notifyDataSetChanged()
        binding.fragmentMenuMenuRecycler.adapter = adapter
    }
}