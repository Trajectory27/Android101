package com.trajectory27.kotlindemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
/**
 * @Author Trajectory27
 * @Description 【码上开学】Kotlin 里那些「更方便的」 作业
 * @Time 2023/5/27 19:13
 */

class Practice3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice3)

        val student = Student("Tim", 20)
        student.show()

        // 用第一个副构造函数
        val student1 = Student("Jack")

        // 用第二个副构造函数
        val student2 = Student(20)

        val list = listOf(21, 40, 11, 33, 78)
        val resultList = mutableListOf<Int>()
        list.forEach {
            if (it % 3 == 0) {
                resultList.add(it)
            }
        }
        println("KotlinDemo $resultList")

    }
}

class Student (private val name: String, private val id: Int) {

    constructor(name: String) : this(name,  10)
    constructor(id: Int) : this("Trajectory27", id)

    fun show() {
        println("KotlinDemo $name $id")
    }
}