package com.trajectory27.flowbasicsdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * @author Trajectory27
 * @description
 * @date 2023/7/24 17:05
 */
@ExperimentalCoroutinesApi
class MeViewModel : ViewModel() {

    private val isAuthenticated = MutableStateFlow(true)
    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val _proFileState = MutableStateFlow<ProfileState?>(null)
    val proFileState = _proFileState.asStateFlow()

    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (10..20).asFlow().onEach { delay(300L) }

    var stringNumber by mutableStateOf("")
        private set

    init {

        /**
         * combine: 将两个流合为一个流，其中任意一个流的值发生改变，即会触发赋值
         */
        user.combine(posts) { user, posts ->
            _proFileState.value = proFileState.value?.copy(
                proFilePicUrl = user?.profilePicUrl,
                userName = user?.userName,
                description = user?.description,
                posts = posts
            )
        }.launchIn(viewModelScope)


        /**
         * 将三个流进行合并
         */
        isAuthenticated.combine(user) { isAuthenticated, user ->
            // lambda返回最后一行值
            if (isAuthenticated) user else null
        }.combine(posts) { user, posts ->
            user?.let {
                _proFileState.value = proFileState.value?.copy(
                    proFilePicUrl = user.profilePicUrl,
                    userName = user.userName,
                    description = user.description,
                    posts = posts
                )

            }
        }.launchIn(viewModelScope)

        /**
         * zip: 合并两个流，当两个流都出现变化时，才会调用
         */
//        flow1.zip(flow2) { number1, number2 ->
//            stringNumber = "($number1,$number2)\n"
//        }.launchIn(viewModelScope)

        /**
         * merge: 合并两个流，作用和combine类似，写法不一样
         */
        merge(flow1, flow2).onEach {
            stringNumber = "$it\n"
        }.launchIn(viewModelScope)

    }


}