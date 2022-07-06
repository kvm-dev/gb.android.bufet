package ru.gb.bufet.model.utils

import android.annotation.SuppressLint
import android.content.Context
import ru.gb.bufet.R
import java.text.SimpleDateFormat
import java.util.*

class ServerUtils {
    @SuppressLint("SimpleDateFormat")
    fun toNormalDate(time: String): Date? {
        var date: Date? = null
        try {
            val normalDate: Date = SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(time) as Date
            date = normalDate
        } catch (e: Exception) {
            println(e)
        }
        return date
    }

    fun checkWorkTime(context: Context, startTime: String, endTime: String):String{
        var result = context.resources.getString(R.string.restaurant_not_open)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDateTime = Date()
        if(ServerUtils().toNormalDate(startTime)!= null && ServerUtils().toNormalDate(endTime)!= null){
            if(currentDateTime > ServerUtils().toNormalDate(startTime)!! && currentDateTime < ServerUtils().toNormalDate(endTime)!!){
                result = "${ServerUtils().toNormalDate(startTime)?.let { timeFormat.format(it) }} - ${ServerUtils().toNormalDate(endTime)
                    ?.let { timeFormat.format(it) }}"
            }
        }
        return result
    }
}