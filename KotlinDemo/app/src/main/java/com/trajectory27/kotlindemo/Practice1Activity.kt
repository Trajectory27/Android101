package com.trajectory27.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

/**
 * @author Trajectory27
 * @description 【码上开学】Kotlin 的变量、函数和类型 作业
 * @date 2023/5/26 10:48
 */

class Practice1Activity : AppCompatActivity() {

    var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice1)

        view = findViewById(R.id.textView)

        getViewId(view)
    }

    private fun getViewId(view: View?) {
        println("KotlinDemo" + view?.id)
    }
}