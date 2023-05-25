package com.trajectory27.coroutinesdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Trajectory27
 * @description
 * @date 2023/5/25 11:41
 */
object RetrofitManager {

    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getRetrofit(service: Class<T>): T {
        return retrofit.create(service)
    }
}