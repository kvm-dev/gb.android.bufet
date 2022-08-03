package ru.gb.bufet.view

import GalleryAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import ru.gb.bufet.MainActivity
import ru.gb.bufet.databinding.FragmentRestaurantBinding
import ru.gb.bufet.model.adapters.TablesAdapter
import ru.gb.bufet.view.base.BaseFragment
import ru.gb.bufet.model.utils.ServerUtils

class RestaurantFragment :
    BaseFragment<FragmentRestaurantBinding>(FragmentRestaurantBinding::inflate) {

    override fun init() {
        binding.backButton.setOnClickListener {
            (activity?.onBackPressed())
        }
        viewModel.currentRestaurant.value.let {
            binding.fragmentRestaurantGallery.adapter =
                it?.restaurantPictures?.let { pictures -> GalleryAdapter(pictures) }

            TabLayoutMediator(binding.fragmentRestaurantGalleryTabs, binding.fragmentRestaurantGallery) { tab, position ->
                tab.text = "${(position + 1)}"
            }.attach()
            binding.fragmentRestaurantTablesRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = it?.restaurantTables?.let { tables ->
                    TablesAdapter(tables)
                }
            }
            it?.restaurantTables.let { tables ->
                binding.fragmentRestaurantTableCounter.text = tables?.size.toString()
            }
            if (it != null) {
                if (it.work_start != null && it.work_end != null) {
                    binding.fragmentRestaurantWorkTime.text =
                        ServerUtils().checkWorkTimeFromTimeStamp(it.work_start, it.work_end)

                }
            }
            binding.fragmentRestaurantDescription.text = it?.description
            binding.title.text = it?.name

            binding.fragmentRestaurantMenuBtn.setOnClickListener {
                (activity as MainActivity).goToFoodMenu()

            }
        }
    }
}