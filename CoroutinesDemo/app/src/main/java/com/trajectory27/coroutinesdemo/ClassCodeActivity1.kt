package com.trajectory27.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class ClassCodeActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }
    }

    private suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("CoroutinesDemo io1 ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode2() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("CoroutinesDemo io2 ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode3() {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            println("CoroutinesDemo io3 ${Thread.currentThread().name}")
        }
    }

    private fun uiCode1() {
        println("CoroutinesDemo ui1 ${Thread.currentThread().name}")
    }

    private fun uiCode2() {
        println("CoroutinesDemo ui2 ${Thread.currentThread().name}")
    }

    private fun uiCode3() {
        println("CoroutinesDemo ui3 ${Thread.currentThread().name}")
    }

}