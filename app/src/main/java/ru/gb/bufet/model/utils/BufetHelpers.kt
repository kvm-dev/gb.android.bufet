package ru.gb.bufet.model.utils

import android.content.Context
import android.view.View

class BufetHelpers(private val context: Context) {
    fun makeVisible(views: ArrayList<View>){
        views.forEach {
            it.visibility = View.VISIBLE
        }
    }
    fun makeInvisible(views: ArrayList<View>){
        views.forEach {
            it.visibility = View.GONE
        }
    }
}