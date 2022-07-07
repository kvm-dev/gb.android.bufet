package ru.gb.bufet.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.Observer
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.databinding.FragmentSplashBinding
import ru.gb.bufet.model.data.BaseFragment
import ru.gb.bufet.model.utils.CheckConnection
import ru.gb.bufet.model.utils.SplashAnimation

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private var timer: CountDownTimer? = null
    override fun init(){
        runAnimation()
        checkConnection()
    }

    private fun runAnimation(){
        val playAnimation =  SplashAnimation(requireContext(), binding.subTitle, listOf(binding.splashItem1, binding.splashItem2, binding.splashItem3, binding.splashItem4, binding.splashItem5))
        playAnimation.splashItemsAnimation()
        playAnimation.subTitleAnimate()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    private fun checkConnection(){
        timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //nothing
            }
            override fun onFinish() {
                if(CheckConnection().isNetworkAvailable(requireContext())){
                    viewModel.getRestaurantsList()
                    viewModel.getAdv()
                    viewModel.restaurantsListResponse.observe(viewLifecycleOwner, Observer {
                        if(it != null){
                            timer?.cancel()
                            (activity as MainActivity).startApplication()
                        }
                    })
                }
                else{
                    (activity as MainActivity).toaster(resources.getString(R.string.connection_error))
                    timer?.cancel()
                    checkConnection()
                }
            }
        }.start()
    }
    }