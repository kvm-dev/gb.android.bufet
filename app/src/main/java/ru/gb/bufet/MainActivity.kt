package ru.gb.bufet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.gb.bufet.databinding.ActivityMainBinding
import ru.gb.bufet.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    //only for test, delete later!!
    private var timer: CountDownTimer? = null
    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main_navigate)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //night mode off
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //no title, no action bar
        Window.FEATURE_NO_TITLE
        supportActionBar?.hide()

        timer = object : CountDownTimer(3000, 1000) {
            @SuppressLint("ZAZLUSHKA")
            override fun onTick(millisUntilFinished: Long) {
                //nothing
            }
            override fun onFinish() {
                timer?.cancel()
                startApplication()
            }
        }.start()
    }

    private fun startApplication(){
        navController.setGraph(R.navigation.navigation)
        binding.navView.visibility = View.VISIBLE
        binding.navView.selectedItemId = R.id.navigation_restaurants
        navController.navigate(R.id.navigation_restaurants)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}