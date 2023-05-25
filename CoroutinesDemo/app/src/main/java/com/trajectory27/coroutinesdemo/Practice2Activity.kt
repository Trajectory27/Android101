package com.trajectory27.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class Practice2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice2)

        val api = RetrofitManager.getRetrofit(Api::class.java)

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                var one = async { api.listReposKt("rengwuxian") }
                var two = async { api.listReposKt("google") }
                findViewById<TextView>(R.id.textView).text = "${one.await()[0].name} -> ${two.await()[0].name}"
            } catch (e: Exception) {
                findViewById<TextView>(R.id.textView).text = e.message
            }

        }
    }
}