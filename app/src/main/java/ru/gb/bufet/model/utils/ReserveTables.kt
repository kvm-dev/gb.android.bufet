package ru.gb.bufet.model.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.EventDay
import ru.gb.bufet.R
import ru.gb.bufet.model.responseData.ReservedTables
import ru.gb.bufet.model.responseData.RestaurantTable
import java.text.SimpleDateFormat
import java.util.*

class ReserveTables(val context: Context, private val table: RestaurantTable?) {

     fun currentDate():Calendar {
         val calendar = Calendar.getInstance()
         calendar.add(Calendar.DATE, -1)
         return calendar
     }

    fun getReservedTables(): List<EventDay> {
        val result = ArrayList<EventDay>()

        table?.reserved?.forEach {
            result.add(distributionEvents(it))
        }
        return result
    }

    private fun distributionEvents(reserveTables: ReservedTables):EventDay{
        val calendar = Calendar.getInstance()
        var eventDay = EventDay(calendar)
        val breakfast = ContextCompat.getDrawable(context, R.drawable.reserved_breakfast)
        val lunch = ContextCompat.getDrawable(context, R.drawable.reserved_lunch)
        val dinner = ContextCompat.getDrawable(context, R.drawable.reserved_dinner)
        val breakfastLunch = ContextCompat.getDrawable(context, R.drawable.reserved_breakfast_lunch)
        val breakfastDinner = ContextCompat.getDrawable(context, R.drawable.reserved_breakfast_dinner)
        val lunchDinner = ContextCompat.getDrawable(context, R.drawable.reserved_lunch_dinner)
        val fullDay = ContextCompat.getDrawable(context, R.drawable.reserved_full_day)
        reserveTables.reservedDate?.let {
            calendar.time = dateFromTimeStamp(it)
        }
        when("${reserveTables.breakfast}${reserveTables.lunch}${reserveTables.dinner}"){
            "100"-> {
                        eventDay = EventDay(calendar, breakfast)
                    }
            "110"-> {
                eventDay = EventDay(calendar, breakfastLunch)
            }
            "101"-> {
                eventDay = EventDay(calendar, breakfastDinner)
            }
            "010"-> {
                eventDay = EventDay(calendar, lunch)
            }
            "011"-> {
                eventDay = EventDay(calendar, lunchDinner)
            }
            "001"-> {
                eventDay = EventDay(calendar, dinner)
            }
            "111"-> {
                eventDay = EventDay(calendar, fullDay)
            }
        }
        return  eventDay
    }

    private fun dateFromTimeStamp(timeStamp: Long): Date{
        val date = Date(timeStamp*1000)
        date.let {
            return it
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun checkReservationTime(eventDay: EventDay): List<Int>{
        var result = listOf(0, 0, 0)
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val selectedEventDay = sdf.format(eventDay.calendar.time)
        table?.reserved?.forEach { reserved ->
            val currentReservedDate = reserved.reservedDate?.let { dateFromTimeStamp(it) }
                ?.let { sdf.format(it) }
            if(selectedEventDay==currentReservedDate){
                result = listOf( reserved.breakfast!!, reserved.lunch!!, reserved.dinner!!)
            }
        }
        return result
    }
    fun availabilityTimeDialog(timeReserveInformation: List<Int>?): List<Boolean>{
        var result = listOf(true, true, true)
        when("${timeReserveInformation?.get(0)}${timeReserveInformation?.get(1)}${timeReserveInformation?.get(2)}"){
            "100"-> {
                result = listOf(false, true, true)
            }
            "110"-> {
                result = listOf(false, false, true)
            }
            "101"-> {
                result = listOf(false, true, false)
            }
            "010"-> {
                result = listOf(true, false, true)
            }
            "011"-> {
                result = listOf(true, false, false)
            }
            "001"-> {
                result = listOf(true, true, false)
            }
            "111"-> {
                result = listOf(false, false, false)
            }
        }
        return result
    }
}