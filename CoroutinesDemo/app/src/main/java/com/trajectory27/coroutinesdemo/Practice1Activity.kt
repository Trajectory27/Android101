package com.trajectory27.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Practice1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice1)

        GlobalScope.launch(Dispatchers.Main) {
            val data = getData()
            val processedData = processData(data)
            findViewById<TextView>(R.id.textView).text = processedData
        }

//        val data = getData()
//        val processedData = processData(data)
        // 练习内容：用协程让上面 ↑ 这两行放在后台执行，然后把代码截图贴到腾讯课堂的作业里
//        findViewById<TextView>(R.id.textView).text = processedData
    }

    private suspend fun getData(): String {
        var str = ""
        withContext(Dispatchers.IO) {
            str = "hen_coder"
        }
        return str
    }

    private suspend fun processData(data: String): String {
        var result = ""
        withContext(Dispatchers.IO) {
            result = data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
            .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
            .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
        }
        return result
    }

//    // 耗时函数 1
//    private fun getData(): String {
//        // 假设这个函数比较耗时，需要放在后台
//        return "hen_coder"
//    }

//    // 耗时函数 2
//    private fun processData(data: String): String {
//        // 假设这个函数也比较耗时，需要放在后台
//        return data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
//            .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
//            .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
//    }
}