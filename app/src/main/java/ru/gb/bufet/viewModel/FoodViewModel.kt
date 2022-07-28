package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.interfaces.API
import ru.gb.bufet.model.responseData.RestaurantFood
import ru.gb.bufet.model.retrofit.RetrofitClient

// а это ты прям целенаправленно хо дернуть еду? если да, то вопросов нет)а вообще у нас по всем рестикам всё ж уже загружено, всё лежит в MainViewModel.currentRestaurant - все по конкретному ресторану
class FoodViewModel: ViewModel() {
    val foodListResponse: MutableLiveData<List<RestaurantFood>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun getFoodList(restaurantId: Int)  {
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

    }
}