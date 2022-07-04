package ru.gb.bufet.model.interfaces

import retrofit2.http.GET
import ru.gb.bufet.model.responseData.FoodListResponse

class API{
    interface GetFoodsAPI {
        @GET("food/")
        suspend fun getFoods(): List<FoodListResponse>
    }
}