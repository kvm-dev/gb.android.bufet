package ru.gb.bufet.model.responseData

data class RestaurantListResponse(
    val description: String?,
    val headerImage: String?,
    val id: Int?,
    val name: String?,
    val tables: ArrayList<TableListResponse>?,
    var type: String?,
    val work_end: String?,
    val work_start: String?
)