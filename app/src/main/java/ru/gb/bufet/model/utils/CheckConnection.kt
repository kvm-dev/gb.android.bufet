package ru.gb.bufet.model.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class CheckConnection {
           fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork      = connectivityManager.activeNetwork ?: return false
        val connection = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            connection.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}