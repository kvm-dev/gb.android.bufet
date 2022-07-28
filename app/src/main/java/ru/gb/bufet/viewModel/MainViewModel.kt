package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.interfaces.API
import ru.gb.bufet.model.responseData.AdvertiseBanners
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.responseData.RestaurantTable
import ru.gb.bufet.model.retrofit.RetrofitClient
import java.util.*

class MainViewModel : ViewModel() {
    //data
    val restaurantsListResponse: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val currentRestaurants: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val currentRestaurant: MutableLiveData<Restaurant> = MutableLiveData()
    val currentTable: MutableLiveData<RestaurantTable> = MutableLiveData()
    val advertiseBanners: MutableLiveData<ArrayList<AdvertiseBanners>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val availableTimeTable: MutableLiveData<List<Int>> = MutableLiveData()
    val reservedTableTime: MutableLiveData<String> = MutableLiveData()
    val reservedTableDate: MutableLiveData<Calendar> = MutableLiveData()

    //methods

    fun getRestaurantsList() {
        val requestData =
            RetrofitClient.RetrofitHelper.getInstance().create(API.GetRestaurantsAPI::class.java)
        viewModelScope.async {
            try {
                restaurantsListResponse.value = requestData.getRestaurants()
                error.value = null
            } catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

    fun getAdv() {
        val requestData =
            RetrofitClient.RetrofitHelper.getInstance().create(API.GetAdvertiseAPI::class.java)
        viewModelScope.async {
            try {
                advertiseBanners.value = requestData.getAdv()
                error.value = null
            } catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

}