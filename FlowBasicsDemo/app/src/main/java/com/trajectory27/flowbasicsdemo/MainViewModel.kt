package com.trajectory27.flowbasicsdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author Trajectory27
 * @description
 * @date 2023/7/19 14:26
 */
class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startValue = 10
        var currentValue = startValue
        emit(startValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    /**
     * StateFlow是Cold Flow，也就是说在没有接收器的情况下，也会进行发射的操作。
     * 主要用途是存储变化的值，在系统的配置改变时（屏幕旋转、黑夜模式）会再次执行一次
     *
     * SharedFlow是Hot Flow，在没有接收器的情况下，不会进行发射的操作。
     * 主要针对是执行一次的事务，比如页面的跳转，在系统配置改变时，不会再执行一次操作
     */

    // 用下划线标识可变、仅存在于MV中的变量
    private val _stateFlow = MutableStateFlow(0)
    // 共用的变量赋值于可变变量
    val stateFlow = _stateFlow.asStateFlow()

    /**
     * 要想让热流在没有接收器的状态下，也能进行发射的操作，可以利用缓存参数 replay = 缓存的次数
     */
    private val _sharedFlow = MutableSharedFlow<Int>(replay = 2)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun squareNumber(number : Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    fun incrementCounter() {
        _stateFlow.value += 1
    }

    init {
//        collectFlow()
        /**
         * SharedFlow属于Hot Flow，在没有收集器的情况下，不会执行发射的操作
         */
//        squareNumber(3)
        viewModelScope.launch {
            sharedFlow.collect{ number ->
                delay(2000L)
                println("FLOW: Current value is $number")
            }
        }

        viewModelScope.launch {
            sharedFlow.collect { number ->
                delay(3000L)
                println("FLOW: Current value is $number")
            }
        }
        squareNumber(3)
    }

    private fun collectFlow1() {
        viewModelScope.launch {
            /**
             * collectLast:只执行最后一数据变化的方法
             */
            val count = countDownFlow
                // 过滤 顾名思义
                .filter { time ->
                    time % 2 == 0
                }

                // 对数值进行一些操作
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    print(time)

                }
                // Terminal Operators 用于最后一个
//                .collect { time ->
//                    println("current time is $time")
//                }
                .count { time ->
                    time % 2 == 0
                }
            println("This is $count")


            // 另一种写法
            countDownFlow.onEach { time ->
                print(time)
            }.launchIn(viewModelScope)
        }
    }

    private fun collectFlow2() {
        viewModelScope.launch {
            val result = countDownFlow
                // 计算器 这里指代第一个和第二个值
                .reduce { accumulator, value ->
                    accumulator + value
                }
            println("The result is $result")
        }

    }

    private fun collectFlow3() {
        viewModelScope.launch {
            val result = countDownFlow
                // 提供一个初始值
                .fold(100) { acc, value ->
                    acc + value
                }
            println("The result is $result")
        }
    }

    private fun collectFlow() {
        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(250L)
            emit("Main dish")
            delay(250L)
            emit("Dessert")
        }

        viewModelScope.launch {
            flow.onEach {
                println("FLOW: $it is delivered")
            }

                // conflate: 任务不排队，直接放弃
//                .conflate()
                // buffer: 在另一个线程中运行collect方法，不会阻塞flow的发送过程
//                .buffer()
                // collect: 会阻塞flow的发送过程
                // collectLatest: 收到新任务，立即放弃当前任务
                .collect {
                    println("FLOW: Now eating $it")
                    delay(1500L)
                    println("FLOW: finished eating $it")
                }
        }
    }

}