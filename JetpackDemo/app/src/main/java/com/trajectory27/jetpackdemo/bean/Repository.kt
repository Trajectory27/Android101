package com.trajectory27.jetpackdemo.bean

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author Trajectory27
 * @description
 * @date 2023/6/15 16:32
 */
object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}