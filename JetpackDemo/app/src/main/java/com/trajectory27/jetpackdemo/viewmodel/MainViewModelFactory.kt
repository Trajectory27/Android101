package com.trajectory27.jetpackdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author Trajectory27
 * @description
 * @date 2023/6/15 11:08
 */
class MainViewModelFactory(private val counterReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(counterReserved) as T
    }
}