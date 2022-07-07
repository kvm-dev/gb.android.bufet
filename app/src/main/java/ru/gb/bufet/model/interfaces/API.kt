package ru.gb.bufet.model.interfaces

import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.bufet.model.responseData.ResponseData

class API{
    interface GetFoodsAPI {
        @GET("food/")
        suspend fun getFoods(@Query("restaurant_id") restaurantId: Int): ArrayList<ResponseData>
    }

    interface GetRestaurantsAPI {
        @GET("fullrest/")
        suspend fun getRestaurants(): ArrayList<ResponseData.Restaurant>
    }

    interface GetAdvertiseAPI {
        @GET("advertise/")
        suspend fun getAdv(): ArrayList<ResponseData.AdvertiseBanners>
    }
}