package com.trajectory27.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Lectures2Activity : AppCompatActivity() {

    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectures2)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)

        /**
         *  使用GlobalScope，如果不在Activity或者Fragment销毁时候
         *  调用job.cancel()，将会导致内存泄露，因此更推荐用lifecycleScope、
         *  viewModelScope
         */
        job = GlobalScope.launch(Dispatchers.Main) {
            try {
                val repos = api.listReposKt("rengwuxian")
                findViewById<TextView>(R.id.textView).text = repos[0].name
            } catch (e: Exception) {
                findViewById<TextView>(R.id.textView).text = e.message
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            val one = async { api.listReposKt("google") }
            val two = async { api.listReposKt("rengwuxian") }
            findViewById<TextView>(R.id.textView).text = "${one.await()[0].name} -> ${two.await()[0].name}"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}