package ru.gb.bufet.model.interfaces

import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.bufet.model.responseData.FoodListResponse

class API{
    interface GetFoodsAPI {
        @GET("food/")
        suspend fun getFoods(@Query("restaurant_id") restaurantId: Int): List<FoodListResponse>
    }
}