package ru.gb.bufet.model.responseData

data class RestaurantTable(
    val availability: Int?,
    val guestId: Int?,
    val guestsCount: Int?,
    val id: Int?,
    val restaurantId: Int?,
    val reserved: List<ReservedTables>?
)
