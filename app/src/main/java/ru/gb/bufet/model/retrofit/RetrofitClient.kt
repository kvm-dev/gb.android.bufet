package ru.gb.bufet.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val  baseURL = "http://141.0.181.194:49872/"
    object RetrofitHelper {
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(RetrofitClient().baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}