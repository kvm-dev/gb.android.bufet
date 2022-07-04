package ru.gb.bufet.retrofit

import retrofit2.Call
import retrofit2.http.GET
import ru.gb.bufet.model.domain.Food

interface FoodService {
    @GET("food")
    fun getFoodList(): Call<List<Food>>
}