package ru.gb.bufet.model.domain

interface FoodRepository {
    fun getAllFood(): List<Food>
}