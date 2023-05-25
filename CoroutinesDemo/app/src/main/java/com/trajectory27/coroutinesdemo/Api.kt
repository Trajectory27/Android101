package com.trajectory27.coroutinesdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Trajectory27
 * @description 网络请求接口
 * @date 2023/5/25 9:39
 */
interface Api {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    suspend fun listReposKt(@Path("user") user: String): List<Repo>
}