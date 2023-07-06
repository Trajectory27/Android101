package com.trajectory27.jetpackdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.trajectory27.jetpackdemo.bean.Repository
import com.trajectory27.jetpackdemo.bean.User

/**
 * @author Trajectory27
 * @description 主界面的ViewModel
 * @date 2023/6/15 9:52
 */
class MainViewModel(counterReserved: Int) : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()
    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    init {
        _counter.value = counterReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}


/*
package com.trajectory27.jetpackdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

*/
/**
 * @author Trajectory27
 * @description 主界面的ViewModel旧写法
 * @date 2023/6/15 9:52
 *//*

class MainViewModel(counterReserved: Int) : ViewModel() {

//    var counter = counterReserved

    val counter = MutableLiveData<Int>()

    init {
        counter.value = counterReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }
}*/
