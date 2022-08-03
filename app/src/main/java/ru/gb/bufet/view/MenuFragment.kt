package ru.gb.bufet.view

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.gb.bufet.databinding.FragmentMenuBinding
import ru.gb.bufet.model.adapters.MenuAdapter
import ru.gb.bufet.view.base.BaseFragment
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.responseData.RestaurantFood

class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {

    override fun init() {
        val restModel = getCurrentRestOrDefault()

        bindingHeader(restModel)
        bindMenuFilter(restModel)
        loadRestaurantMenu(restModel)

        //todo сделать едуную точку вызова рендера с передачей результатов поиска и фильтрации
    }


    private fun getCurrentRestOrDefault(): Restaurant {
        val restaurantModel = viewModel.currentRestaurant.value
        return restaurantModel!!
        //todo передать бы сюда какой-то дефолтный рест, если карент null
    }

    private fun bindingHeader(restModel: Restaurant) {
        binding.header.headerBackBtn.setOnClickListener {
            (activity?.onBackPressed())
        }
        binding.header.headerTitleTv.text = restModel.name
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
                renderFoodList(searchByName(restModel, searchText))
                return true
            }
        })
    }

    private fun searchByName(restModel: Restaurant, searchText: String): ArrayList<RestaurantFood> {
        val filteredList = ArrayList<RestaurantFood>()
        for (restFood in restModel.restaurantFood!!) {
            if (restFood.name!!.lowercase().contains(searchText.lowercase()))
                filteredList.add(restFood)
        }
        return filteredList
    }

    private fun searchByType(restModel: Restaurant, searchType: String): ArrayList<RestaurantFood> {
        val filteredList = ArrayList<RestaurantFood>()
        if (searchType == "все") {
            filteredList.addAll(restModel.restaurantFood!!)
        } else {
            for (restFood in restModel.restaurantFood!!) {
                if (restFood.type!!.lowercase().contains(searchType.lowercase()))
                    filteredList.add(restFood)
            }
        }
        return filteredList
    }

    private fun bindMenuFilter(restModel: Restaurant) {
        val filterTabs = mutableSetOf<String>()
        for (i in 0..restModel.restaurantFood!!.lastIndex) {
            filterTabs.add(restModel.restaurantFood[i].type.toString())
        }
        binding.fragmentMenuFilterFood.addTab(
            binding.fragmentMenuFilterFood.newTab().setText("все")
        )
        filterTabs.forEach {
            binding.fragmentMenuFilterFood.addTab(
                binding.fragmentMenuFilterFood.newTab().setText(it)
            )
        }

        binding.fragmentMenuFilterFood.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                searchByType(restModel, tab?.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

//    private fun renderFoodList(searchedArray: ArrayList<RestaurantFood>, typArray: ArrayList<RestaurantFood>) {
//
//        val filteredList = searchedArray.intersect(typArray.toSet())
//        val adapter = MenuAdapter(filteredList.toList())
//        adapter.notifyDataSetChanged()
//        binding.fragmentMenuMenuRecycler.adapter = adapter
//    }
//
    private fun renderFoodList(searchedArray: ArrayList<RestaurantFood>) {
        val adapter = MenuAdapter(searchedArray)
        adapter.notifyDataSetChanged()
        binding.fragmentMenuMenuRecycler.adapter = adapter
    }
}