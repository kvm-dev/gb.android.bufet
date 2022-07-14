package ru.gb.bufet.model.responseData

import com.google.gson.annotations.SerializedName

data class RestaurantFood(
    val id: Int?,
    val name: String?,
    val pictures: String?,
    val price: String?,
    @SerializedName("restaurant_id")
    val restaurantId: Int?,
    val type: String?
)
