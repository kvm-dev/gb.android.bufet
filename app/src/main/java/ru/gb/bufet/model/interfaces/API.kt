package ru.gb.bufet.model.interfaces

import retrofit2.http.GET
import retrofit2.http.Path
import ru.gb.bufet.model.responseData.AdvertiseBanners
import ru.gb.bufet.model.responseData.Restaurant
import ru.gb.bufet.model.responseData.RestaurantFood

class API{
    interface GetFoodsAPI {
        @GET("food/{restaurant_id}")
        suspend fun getFoods(@Path("restaurant_id") restaurantId: Int): ArrayList<RestaurantFood>
    }
    interface GetAllFoodsAPI {
        @GET("food/")
        suspend fun getAllFoods(): ArrayList<RestaurantFood>
    }

    interface GetRestaurantsAPI {
        @GET("fullrest/")
        suspend fun getRestaurants(): ArrayList<Restaurant>
    }

    interface GetAdvertiseAPI {
        @GET("advertise/")
        suspend fun getAdv(): ArrayList<AdvertiseBanners>
    }
}