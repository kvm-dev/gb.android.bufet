package ru.gb.bufet.view

import GalleryAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ru.gb.bufet.databinding.FragmentRestaurantBinding
import ru.gb.bufet.model.data.BaseFragment

class RestaurantFragment: BaseFragment<FragmentRestaurantBinding>(FragmentRestaurantBinding::inflate) {

    override fun init(){

        binding.backButton.setOnClickListener {
            (activity?.onBackPressed())
        }
        viewModel.currentRestaurant.value.let {
                binding.gallery.adapter = it?.restaurantPictures?.let { it1 -> GalleryAdapter(it1) }

            TabLayoutMediator(binding.galleryTabs, binding.gallery) { tab, position ->
                tab.text = "${(position + 1)}"
            }.attach()
        }
    }
    }