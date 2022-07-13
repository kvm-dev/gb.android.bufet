package ru.gb.bufet.model.responseData

data class Restaurant(
    val description: String?,
    val headerImage: String?,
    val id: Int?,
    val name: String?,
    val restaurantFood: List<RestaurantFood>?,
    val restaurantPictures: List<RestaurantPicture>?,
    val restaurantTables: List<RestaurantTable>?,
    val type: String?,
    val work_end: Long?,
    val work_start: Long?
)
