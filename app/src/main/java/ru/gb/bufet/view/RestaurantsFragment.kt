package ru.gb.bufet.view

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.tabs.TabLayout
import ru.gb.bufet.databinding.FragmentRestaurantsBinding
import ru.gb.bufet.model.adapters.AdvertiseAdapter
import ru.gb.bufet.model.adapters.RestaurantsAdapter
import ru.gb.bufet.model.responseData.AdvertiseBanners
import ru.gb.bufet.model.utils.BufetHelpers
import ru.gb.bufet.model.utils.RestaurantUtils
import ru.gb.bufet.view.base.BaseFragment


class RestaurantsFragment : BaseFragment<FragmentRestaurantsBinding>(
    FragmentRestaurantsBinding::inflate
) {
    private lateinit var adapter: RestaurantsAdapter
    override fun init() {
        loadRestaurants()
        searchFoodByKeyWord()
        loadBanners()
    }

    private fun loadRestaurants() {
        if (viewModel.restaurantsListResponse.value != null) {
            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
        }
        val verticalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.fragmentRestaurantsRecycler.layoutManager = verticalLayoutManager
        adapter = viewModel.currentRestaurants.value?.let {
            RestaurantsAdapter(it)
        }!!
        binding.fragmentRestaurantsRecycler.adapter = adapter
        loadFilterNavigation(RestaurantUtils(requireContext()).getRestaurantsTypes())
    }

    private fun loadFilterNavigation(filters: Array<String>) {
        filters.forEach {
            binding.filterNavigation.addTab(binding.filterNavigation.newTab().setText(it))
        }
        binding.filterNavigation.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //filtration
                if (!binding.filterNavigation.getTabAt(tab.position)?.text.isNullOrEmpty()) {
                    RestaurantUtils(requireContext()).filter(
                        binding.filterNavigation.getTabAt(
                            tab.position
                        )?.text as String
                    )
                    binding.fragmentRestaurantsRecycler.adapter =
                        viewModel.currentRestaurants.value?.let {
                            RestaurantsAdapter(it)
                        }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun searchFoodByKeyWord() {

        binding.fragmentRestaurantsSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                RestaurantUtils(requireContext()).search(text.toString())
                binding.fragmentRestaurantsRecycler.adapter =
                    viewModel.currentRestaurants.value?.let {
                        RestaurantsAdapter(it)
                    }
                if (binding.fragmentRestaurantsSearchView.query.isEmpty()) {
                    binding.filterNavigation.getTabAt(0)?.select()
                }
                if (viewModel.currentRestaurants.value.isNullOrEmpty()) {
                    BufetHelpers(requireContext()).makeVisible(
                        arrayListOf(
                            binding.searchSpacer,

                            binding.fragmentRestaurantsItemNotFound.itemNotFoundCard
                        )
                    )
                    BufetHelpers(requireContext()).makeInvisible(
                        arrayListOf(
                            binding.filterNavigation,
                            binding.advertiseRecycler,
                            binding.fragmentRestaurantsRecycler
                        )
                    )
                } else {
                    BufetHelpers(requireContext()).makeVisible(
                        arrayListOf(
                            binding.filterNavigation,
                            binding.advertiseRecycler,
                            binding.fragmentRestaurantsRecycler
                        )
                    )
                    BufetHelpers(requireContext()).makeInvisible(
                        arrayListOf(
                            binding.searchSpacer,
                            binding.fragmentRestaurantsItemNotFound.itemNotFoundCard
                        )
                    )
                }
                return false
            }
        })
    }

    private fun loadBanners() {
        viewModel.advertiseBanners.observe(viewLifecycleOwner, Observer { advertisement ->
            if (advertisement != null) {
                binding.advertiseRecycler.visibility = View.VISIBLE
                val sliderLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.advertiseRecycler.layoutManager = sliderLayoutManager
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(binding.advertiseRecycler)
                binding.advertiseRecycler.adapter =
                    AdvertiseAdapter(advertisement.filter { it.isActive == true } as ArrayList<AdvertiseBanners>)
            }
        })
    }
}