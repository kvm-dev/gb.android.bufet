package ru.gb.bufet

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.gb.bufet.databinding.ActivityMainBinding
import ru.gb.bufet.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
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
        //connection Error Checker
        viewModel.error.observe(this, Observer {
            if(it != null){
                toaster(it)
                viewModel.error.value = null
            }
        })
    }

     fun startApplication(){
        navController.setGraph(R.navigation.navigation)
        binding.navView.visibility = View.VISIBLE
        binding.navView.selectedItemId = R.id.navigation_restaurants
        navController.navigate(R.id.navigation_restaurants)
    }
    //navigation methods
    fun goToRestaurant(){
        navController.navigate(R.id.navigation_restaurant)
    }

    fun goToReserve(){
        navController.navigate(R.id.navigation_reserve)
    }
    fun goToFoodMenu(){
        navController.navigate(R.id.navigation_food_menu)
    }


    fun toaster(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //hide keyboard
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val focusedView = currentFocus
            if (focusedView is EditText) {
                focusedView.clearFocus()
                val imm: InputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }
}