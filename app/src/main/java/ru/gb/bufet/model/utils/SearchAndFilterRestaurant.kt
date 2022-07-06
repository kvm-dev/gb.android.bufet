package ru.gb.bufet.model.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.viewModel.MainViewModel

class SearchAndFilterRestaurant(private val context: Context) {
    private val activity : MainActivity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
    fun filter(key: String){
        if(viewModel.restaurantsListResponse.value != null && key.isNotEmpty()){
            if (key==context.resources.getStringArray(R.array.main_display_title_tabs)[0]){
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
            }
            else{
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter { it.type!!.contains(key)}
            }
        }
    }

    fun search(key: String){
        if(viewModel.restaurantsListResponse.value != null && key.isNotEmpty()){
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter { it.name!!.contains(key)}
        }
        else{
            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter { it.type!!.contains(key)}
        }
    }
}