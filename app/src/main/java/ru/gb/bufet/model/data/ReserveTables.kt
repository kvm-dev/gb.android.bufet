package ru.gb.bufet.model.data

import android.content.Context
import androidx.core.content.ContextCompat
import com.applandeo.materialcalendarview.EventDay
import ru.gb.bufet.R
import ru.gb.bufet.model.responseData.ReservedTables
import ru.gb.bufet.model.responseData.RestaurantTable
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
        val date = reserveTables.reservedDate?.let { reservedDate -> Date(reservedDate*1000) }
        date.let {
            if (date != null) {
                calendar.time = date
            }
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
}