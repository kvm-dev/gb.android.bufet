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
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    //data
    val restaurantsListResponse: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData() //full rest api response, default response
    val currentRestaurants: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData() // restaurants in adapter of RestaurantsFragment
    val currentRestaurant: MutableLiveData<Restaurant> = MutableLiveData() // selected restaurant of restaurants recycler view
    val currentTable: MutableLiveData<RestaurantTable> = MutableLiveData() // selected table of restaurant's table recycler view
    val restaurantsTypes: MutableLiveData<Array<String>> = MutableLiveData() // all restaurant's type from server response
    val advertiseBanners: MutableLiveData<ArrayList<AdvertiseBanners>> = MutableLiveData() // advertise list
    val error: MutableLiveData<String> = MutableLiveData() // responses error
    val availableTimeTable: MutableLiveData<List<Int>> = MutableLiveData() // available time's list for reserve table
    val reservedTableTime: MutableLiveData<String> = MutableLiveData() // unavailable time's list for reserve table
    val reservedTableDate: MutableLiveData<Calendar> = MutableLiveData() // reserved table date by our customer
    val paymentCardNumberValid : MutableLiveData<Boolean> = MutableLiveData() //for payment reserve table validation
    val paymentCardExpirationValid : MutableLiveData<Boolean> = MutableLiveData() //for payment reserve table validation
    val paymentCardCvvValid : MutableLiveData<Boolean> = MutableLiveData() //for payment reserve table validation
    val paymentCodeValid : MutableLiveData<Boolean> = MutableLiveData() //for payment reserve table validation

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