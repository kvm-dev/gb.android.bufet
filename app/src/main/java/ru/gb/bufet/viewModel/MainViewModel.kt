package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.retrofit.RetrofitClient
import ru.gb.bufet.model.interfaces.API
import ru.gb.bufet.model.responseData.AdvertiseBanners
import ru.gb.bufet.model.responseData.RestaurantTable

class  MainViewModel: ViewModel() {
    //data
    val responseData: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val restaurantsListResponse: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val currentRestaurants: MutableLiveData<ArrayList<Restaurant>> = MutableLiveData()
    val currentRestaurant: MutableLiveData<Restaurant> = MutableLiveData()
    val currentTable: MutableLiveData<RestaurantTable> = MutableLiveData()
    val advertiseBanners: MutableLiveData<ArrayList<AdvertiseBanners>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    //methods
    fun getFoodList(restaurantId: Int) {
        val requestData = RetrofitClient.RetrofitHelper.getInstance().create(API.GetFoodsAPI::class.java)
        viewModelScope.async {
            try{
                responseData.value = requestData.getFoods(restaurantId)
                error.value = null
            }
            catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

    fun getRestaurantsList() {
        val requestData = RetrofitClient.RetrofitHelper.getInstance().create(API.GetRestaurantsAPI::class.java)
        viewModelScope.async {
            try{
                restaurantsListResponse.value = requestData.getRestaurants()
                error.value = null
            }
            catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

    fun getAdv() {
        val requestData = RetrofitClient.RetrofitHelper.getInstance().create(API.GetAdvertiseAPI::class.java)
        viewModelScope.async {
            try{
                advertiseBanners.value = requestData.getAdv()
                error.value = null
            }
            catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

}