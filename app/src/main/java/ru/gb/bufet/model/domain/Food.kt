package ru.gb.bufet.model.domain

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("type")
    val type: String? = "default",
    @SerializedName("name")
    val name: String? = "default",
    @SerializedName("pictures")
    val pictures: String? = "default",
    @SerializedName("price")
    val price: String? = "default",
    @SerializedName("restaurant_id")
    val restaurantId: Int = 0
)
