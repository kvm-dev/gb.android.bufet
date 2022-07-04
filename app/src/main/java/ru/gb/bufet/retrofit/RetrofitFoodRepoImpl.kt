package ru.gb.bufet.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.bufet.model.domain.Food
import ru.gb.bufet.model.domain.FoodRepository

const val BASE_URL = "http://141.0.181.194:49872/"

class RetrofitFoodRepoImpl : FoodRepository {

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val foodService = retrofit.create(FoodService::class.java)

    override fun getAllFood(): List<Food> =
        foodService.getFoodList().execute().body() ?: emptyList<Food>()
}