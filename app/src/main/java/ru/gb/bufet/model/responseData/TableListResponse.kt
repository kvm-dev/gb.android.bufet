package ru.gb.bufet.model.responseData

data class TableListResponse(
    val availability: Int?,
    val guestId: Int?,
    val guestsCount: Int?,
    val id: Int?,
    val restaurantId: Int?
)