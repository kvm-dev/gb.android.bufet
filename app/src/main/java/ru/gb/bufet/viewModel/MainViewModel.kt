package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.responseData.ResponseData
import ru.gb.bufet.model.retrofit.RetrofitClient
import ru.gb.bufet.model.interfaces.API

class  MainViewModel: ViewModel() {
    //data
    val responseData: MutableLiveData<List<ResponseData>> = MutableLiveData()
    val restaurantsListResponse: MutableLiveData<ArrayList<ResponseData.Restaurant>> = MutableLiveData()
    val currentRestaurants: MutableLiveData<ArrayList<ResponseData.Restaurant>> = MutableLiveData()
    val currentRestaurant: MutableLiveData<ResponseData.Restaurant> = MutableLiveData()
    val advertiseBanners: MutableLiveData<ArrayList<ResponseData.AdvertiseBanners>> = MutableLiveData()
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