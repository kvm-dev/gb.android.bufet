package ru.gb.bufet.view

import ru.gb.bufet.databinding.FragmentSplashBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.utils.SplashAnimation

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun init(){
        runAnimation()
    }

    private fun runAnimation(){
        val playAnimation =  SplashAnimation(requireContext(), binding.subTitle, listOf(binding.splashItem1, binding.splashItem2, binding.splashItem3, binding.splashItem4, binding.splashItem5))
        playAnimation.splashItemsAnimation()
        playAnimation.subTitleAnimate()
    }
}