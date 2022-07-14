package ru.gb.bufet.model.utils

import android.annotation.SuppressLint
import android.content.Context
import ru.gb.bufet.R
import java.text.SimpleDateFormat
import java.util.*

class ServerUtils {
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun toNormalDateFromString(time: String): Date? {
        var date: Date? = null
        try {
            val normalDate: Date = SimpleDateFormat("YYYY-MM-DD HH:MM:SS").parse(time) as Date
            date = normalDate
        } catch (e: Exception) {
            println(e)
        }
        return date
    }

    fun checkWorkTimeFromStrings(context: Context, startTime: String, endTime: String):String{
        var result = context.resources.getString(R.string.restaurant_not_open)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDateTime = Date()
        if(ServerUtils().toNormalDateFromString(startTime)!= null && ServerUtils().toNormalDateFromString(endTime)!= null){
            if(currentDateTime > ServerUtils().toNormalDateFromString(startTime)!! && currentDateTime < ServerUtils().toNormalDateFromString(endTime)!!){
                result = "${ServerUtils().toNormalDateFromString(startTime)?.let { timeFormat.format(it) }} - ${ServerUtils().toNormalDateFromString(endTime)
                    ?.let { timeFormat.format(it) }}"
            }
        }
        return result
    }

    private fun getNormalTimeFromTimeStamp(type:String, timestamp:Long):Int{
        //minutes
        var result = 0
        val timestampLong = timestamp * 1000
        val d = Date(timestampLong)
        val c = Calendar.getInstance()
        c.time = d
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val date = c[Calendar.DATE]
        val minutes = c[Calendar.MINUTE]
        val hours = c[Calendar.HOUR_OF_DAY]
        result = if(type=="min") {
            minutes
        }
        //hours
        else {
            hours
        }
        return result
    }


    fun checkWorkTimeFromTimeStamp(context: Context, startTime: Long, endTime: Long):String{
//        var result = context.resources.getString(R.string.restaurant_not_open)
        var result = ""
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDateTime = timeFormat.format(Date())
        val workStart = timeFormat.format(Date(startTime))
        val workEnd = timeFormat.format(Date(endTime))
        if(currentDateTime>workStart && currentDateTime< workEnd){
            result = "$workStart - $workEnd"
        }
        return result
    }
}