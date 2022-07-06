package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.responseData.FoodListResponse
import ru.gb.bufet.model.retrofit.RetrofitClient
import ru.gb.bufet.model.interfaces.API
import ru.gb.bufet.model.responseData.RestaurantListResponse

class  MainViewModel: ViewModel() {
    //data
    val foodListResponse: MutableLiveData<List<FoodListResponse>> = MutableLiveData()
    val restaurantsListResponse: MutableLiveData<List<RestaurantListResponse>> = MutableLiveData()
    val currentRestaurants: MutableLiveData<List<RestaurantListResponse>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    //methods
    fun getFoodList(restaurantId: Int) {
        val requestData = RetrofitClient.RetrofitHelper.getInstance().create(API.GetFoodsAPI::class.java)
        viewModelScope.async {
            try{
                foodListResponse.value = requestData.getFoods(restaurantId)
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

}