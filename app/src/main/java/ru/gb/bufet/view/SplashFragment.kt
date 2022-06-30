package ru.gb.bufet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentSplashBinding
import ru.gb.bufet.viewModel.MainViewModel


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    //animationData
    private var currentAnimation = 0
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        init()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun init(){
        runAnimation()
    }

    //food animation
    private fun splashItemsAnimation(){
        val animation: Animation = if(currentAnimation==0){
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_move_item_move_up)
        } else{
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_down)
        }
        val animation2: Animation = if(currentAnimation==0){
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_right)
        } else{
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_left)
        }
        val animation3: Animation = if(currentAnimation==0){
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_down)
        } else{
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_move_item_move_up)
        }
        val animation4: Animation = if(currentAnimation==0){
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_left)
        } else{
            AnimationUtils.loadAnimation(requireContext(), R.anim.splash_item_move_right)
        }
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                currentAnimation = if(currentAnimation==0) 1 else 0
                splashItemsAnimation()
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.splashItem1.startAnimation(animation)
        binding.splashItem2.startAnimation(animation4)
        binding.splashItem3.startAnimation(animation3)
        binding.splashItem4.startAnimation(animation2)
        binding.splashItem5.startAnimation(animation3)
    }

    private fun runAnimation(){
        //subtitle animation
        binding.subTitle.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.subtitle))
        splashItemsAnimation()
    }
}