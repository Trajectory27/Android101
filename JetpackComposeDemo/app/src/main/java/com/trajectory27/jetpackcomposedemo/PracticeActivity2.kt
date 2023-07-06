package com.trajectory27.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trajectory27.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
/**
 * @Author Trajectory27
 * @Description
 * @Time 2023/6/6 23:04
 */
class PracticeActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Andy", "Compose"))
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Text(text = msg.author)
        Text(text = msg.body)
    }
    
}

data class Message(val author: String, val body: String)
