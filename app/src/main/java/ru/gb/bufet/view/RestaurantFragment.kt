package ru.gb.bufet.view

import ru.gb.bufet.databinding.FragmentRestaurantBinding
import ru.gb.bufet.model.data.BaseFragment

class RestaurantFragment: BaseFragment<FragmentRestaurantBinding>(FragmentRestaurantBinding::inflate) {

    override fun init(){

        binding.backButton.setOnClickListener {
            (activity?.onBackPressed())
        }
        //some initialization
        //https://medium.com/@mandvi2346verma/image-slider-with-dot-indicators-using-viewpager-firebase-kotlin-android-735968da76f6
    }
}