package ru.gb.bufet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.interfaces.API
import ru.gb.bufet.model.responseData.RestaurantFood
import ru.gb.bufet.model.retrofit.RetrofitClient


class FoodViewModel: ViewModel() {
    val foodListResponse: MutableLiveData<List<RestaurantFood>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun getFoodList(restaurantId: Int) : LiveData<List<RestaurantFood>> {
        val restFoodData =
            RetrofitClient.RetrofitHelper.getInstance().create(API.GetFoodsAPI::class.java)
        viewModelScope.async {
            try {
                foodListResponse.value = restFoodData.getFoods(restaurantId)
                error.value = null
            } catch (e: Exception) {
                error.value = e.toString()
            }
        }
        return foodListResponse
    }
}