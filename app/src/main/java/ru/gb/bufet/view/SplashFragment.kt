package ru.gb.bufet.view

import android.util.Log
import androidx.lifecycle.Observer
import ru.gb.bufet.databinding.FragmentSplashBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.utils.SplashAnimation

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun init(){
        runAnimation()
        //test food
        viewModel.getFoodList(0)
        viewModel.foodListResponse.observe(viewLifecycleOwner, Observer {
            if(it != null){
                Log.d("ответ", it.toString())
            }
        })
    }

    private fun runAnimation(){
        val playAnimation =  SplashAnimation(requireContext(), binding.subTitle, listOf(binding.splashItem1, binding.splashItem2, binding.splashItem3, binding.splashItem4, binding.splashItem5))
        playAnimation.splashItemsAnimation()
        playAnimation.subTitleAnimate()
    }
}