package ru.gb.bufet.model.responseData

class ResponseData
{
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

data class RestaurantFood(
    val id: Int?,
    val name: String?,
    val pictures: String?,
    val price: String?,
    val restaurantId: Int?,
    val type: String?
)

data class RestaurantPicture(
    val id: Int?,
    val imagePath: String?,
    val restaurantId: Int?
)

data class RestaurantTable(
    val availability: Int?,
    val guestId: Int?,
    val guestsCount: Int?,
    val id: Int?,
    val restaurantId: Int?
)
    data class AdvertiseBanners(
    val description: String?,
    val id: Int?,
    val image: String?,
    val isActive: Boolean?,
    val title: String?
)
}
