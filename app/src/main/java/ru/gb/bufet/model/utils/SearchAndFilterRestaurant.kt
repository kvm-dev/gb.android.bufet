package ru.gb.bufet.model.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import ru.gb.bufet.MainActivity
import ru.gb.bufet.R
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.viewModel.MainViewModel
import java.util.*
import kotlin.collections.ArrayList

class SearchAndFilterRestaurant(private val context: Context) {
    private val activity : MainActivity = context as MainActivity
    private val viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
    fun filter(key: String){
        if(viewModel.restaurantsListResponse.value != null && key.isNotEmpty()){
            val keyword = key.lowercase(Locale.getDefault())
            if (keyword==context.resources.getStringArray(R.array.main_display_title_tabs)[0].lowercase(Locale.getDefault())){
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
            }
            else{
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter {
                    it.type?.lowercase(Locale.getDefault())?.contains(keyword) == true
                } as ArrayList<Restaurant>?
            }
        }
        else{
            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
        }
    }

    fun search(key: String){
        if(viewModel.restaurantsListResponse.value != null && key.isNotEmpty()){
            val keyword = key.lowercase(Locale.getDefault())
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter {
                    it.name?.lowercase(Locale.getDefault())?.contains(keyword) == true
                } as ArrayList<Restaurant>?
        if(viewModel.restaurantsListResponse.value.isNullOrEmpty()){
            viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value?.filter {
                it.type?.lowercase(Locale.getDefault())?.contains(keyword) == true
            } as ArrayList<Restaurant>?
        }
        }
        else{
            if(viewModel.restaurantsListResponse.value !=null){
                viewModel.currentRestaurants.value = viewModel.restaurantsListResponse.value
            }
        }
    }
}