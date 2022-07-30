package ru.gb.bufet.view

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isNotEmpty
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
        loadRestaurantMenu(restModel)
        bindingHeader(restModel)

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
        if (binding.fragmentMenuSearchView.query.isEmpty()) {
            val adapter = restModel.restaurantFood?.let {
                MenuAdapter(it)
            }
            adapter!!.notifyDataSetChanged()
            binding.fragmentMenuMenuRecycler.adapter = adapter
        } else {
            search(restModel)
        }
    }

    private fun search(restModel: Restaurant) {

        binding.fragmentMenuSearchView.clearFocus()

//        if (binding.fragmentMenuSearchView.query.isNotEmpty()) {

        binding.fragmentMenuSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                val filteredList = ArrayList<RestaurantFood>()
                for (restFood in restModel.restaurantFood!!) {
                    if (restFood.name!!.lowercase().contains(searchText.lowercase()))
                        filteredList.add(restFood)
                }

                val adapter = MenuAdapter(filteredList)
                adapter.notifyDataSetChanged()
                binding.fragmentMenuMenuRecycler.adapter = adapter

                return true
            }
        })
//        } else {
//            val adapter = restModel.restaurantFood?.let {
//                MenuAdapter(it)
//            }
//            adapter!!.notifyDataSetChanged()
//            binding.fragmentMenuMenuRecycler.adapter = adapter
//        }
    }
}
