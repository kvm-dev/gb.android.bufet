package ru.gb.bufet.model.responseData

data class ReservedTables(
    val tableId: Int?,
    val reservedDate: Long?,
    val breakfast: Int?,
    val lunch: Int?,
    val dinner: Int?,
    val id: Int?
)
