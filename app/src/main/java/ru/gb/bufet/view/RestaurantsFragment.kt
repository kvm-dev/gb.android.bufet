package ru.gb.bufet.view

import android.util.Log
import android.view.View
import android.widget.Toast
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
                return true
            }
        })


        if (viewModel.currentRestaurants.value.isNullOrEmpty()) {
            BufetHelpers(requireContext()).makeVisible(
                arrayListOf(
                    binding.searchSpacer,
//                    binding.cancelSearch,
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
//                    binding.cancelSearch,
                    binding.fragmentRestaurantsItemNotFound.itemNotFoundCard
                )
            )
        }

        binding.fragmentRestaurantsSearchView.setOnCloseListener {
            binding.filterNavigation.getTabAt(0)?.select()
            Toast.makeText(requireContext(), "clear", Toast.LENGTH_SHORT).show()
            true
        }

//        binding.cancelSearch.setOnClickListener {
//            binding.search.text?.clear()
//            val imm: InputMethodManager =
//                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(binding.search.windowToken, 0)
//            binding.filterNavigation.getTabAt(0)?.select()
//        }
//        if (binding.search.text.isNullOrEmpty()) {
//            binding.filterNavigation.visibility = View.VISIBLE
//        } else {
//            binding.filterNavigation.visibility = View.GONE
//            binding.filterNavigation.getTabAt(0)?.select()
//        }
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