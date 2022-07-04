package ru.gb.bufet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.gb.bufet.model.responseData.FoodListResponse
import ru.gb.bufet.model.retrofit.RetrofitClient
import ru.gb.bufet.model.interfaces.API

class  MainViewModel: ViewModel() {
    //data
    val foodListResponse: MutableLiveData<List<FoodListResponse>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    //methods
    fun getFoodList(restaurantId: Int) {
        val requestData = RetrofitClient.RetrofitHelper.getInstance().create(API.GetFoodsAPI::class.java)
        viewModelScope.async {
            try{
                foodListResponse.value = requestData.getFoods(restaurantId)
            }
            catch (e: Exception) {
                error.value = e.toString()
            }
        }
    }

}