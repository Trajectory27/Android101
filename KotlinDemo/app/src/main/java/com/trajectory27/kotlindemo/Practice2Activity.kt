package com.trajectory27.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * @Author Trajectory27
 * @Description 【码上开学】Kotlin 里那些「不是那么写的」 作业
 * @Time 2023/5/27 17:21
 */
class Practice2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice2)

        createArray()
        createIntArray()
        createList()

        val sample = Sample.getSample()

    }

    private fun createArray() {
        val initTime = System.currentTimeMillis()
        val array: Array<Int> = Array(1000000) {
            it + 1
        }
        val endTime = System.currentTimeMillis()
        println("KotlinDemo Array ${array.average()} time is:  ${endTime - initTime}")
    }

    private fun createIntArray() {
        val initTime = System.currentTimeMillis()
        val intArray: IntArray = IntArray(1000000) {
            it + 1
        }
        val endTime = System.currentTimeMillis()
        println("KotlinDemo IntArray ${intArray.average()} time is:  ${endTime - initTime}")
    }

    private fun createList() {
        val initTime = System.currentTimeMillis()
        val list = List(1000000) {
            it + 1
        }
        val endTime = System.currentTimeMillis()
        println("KotlinDemo IntArray ${list.average()} time is:  ${endTime - initTime}")
    }
}

/**
 *  私有化构造器，在伴生类中提供返回自身的方法
 */
class Sample private constructor() {

    companion object {
        fun getSample(): Sample {
            return Sample()
        }
    }
}