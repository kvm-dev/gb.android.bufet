package ru.gb.bufet.view

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentRestaurantsBinding
import ru.gb.bufet.model.adapters.RestaurantsAdapter
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.utils.SearchAndFilterRestaurant

class RestaurantsFragment : BaseFragment<FragmentRestaurantsBinding>(
    FragmentRestaurantsBinding::inflate
) {
    private lateinit var adapter: RestaurantsAdapter
    override fun init() {
        loadRestaurants()
        searchFoodByKeyWord()
    }

    private fun loadRestaurants(){
        if(viewModel.restaurantsListResponse.value != null){
            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
            //test
            viewModel.currentRestaurants.value!![3].type = "ФастФуд"
        }
        val verticalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
        binding.restaurantsRecycler.layoutManager = verticalLayoutManager
        adapter = viewModel.currentRestaurants.value?.let {
            RestaurantsAdapter(it)
        }!!
        binding.restaurantsRecycler.adapter = adapter
        val testFilterList = resources.getStringArray(R.array.main_display_title_tabs)
        loadFilterNavigation(testFilterList)
    }

    private fun loadFilterNavigation(filters:Array<String>){
        filters.forEach {
            binding.filterNavigation.addTab(binding.filterNavigation.newTab().setText(it))
        }
        binding.filterNavigation.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //filtration
                if(!binding.filterNavigation.getTabAt(tab.position)?.text.isNullOrEmpty()){
                    SearchAndFilterRestaurant(requireContext()).filter(binding.filterNavigation.getTabAt(tab.position)?.text as String)
                    binding.restaurantsRecycler.adapter = viewModel.currentRestaurants.value?.let {
                        RestaurantsAdapter(it)}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun searchFoodByKeyWord(){
        binding.search.doOnTextChanged { text, _, _, _ ->
                SearchAndFilterRestaurant(requireContext()).search(text.toString())
                binding.restaurantsRecycler.adapter = viewModel.currentRestaurants.value?.let {
                    RestaurantsAdapter(it)}}
}
}