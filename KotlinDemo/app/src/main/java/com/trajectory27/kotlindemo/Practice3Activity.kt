package com.trajectory27.kotlindemo

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.sql.Time
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

    constructor(Person: Person) : this("rengwuxian", 10)
    constructor(Time: Time) : this("Trajectory27", 20)

    fun show() {
        println("KotlinDemo $name $id")
    }
}