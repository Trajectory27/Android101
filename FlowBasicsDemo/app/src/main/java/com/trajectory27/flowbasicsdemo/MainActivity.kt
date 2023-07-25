package com.trajectory27.flowbasicsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trajectory27.flowbasicsdemo.ui.theme.FlowBasicsDemoTheme

/**
 * @author Trajectory27
 * @description Kotlin Flow Practice
 * @date 2023/7/19 14:25
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowBasicsDemoTheme {

//                val viewModel = viewModel<MainViewModel>()
//                val time = viewModel.countDownFlow.collectAsState(initial = 10)
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                ) {
//                    Text(
//                        text = time.value.toString(),
//                        fontSize = 30.sp,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }


                val viewModel = viewModel<MainViewModel>()
                val counter = viewModel.stateFlow.collectAsState(0)

                Button(onClick = {
                    viewModel.incrementCounter()
                }) {
                    Text("Counter: ${counter.value}")
                }

            }
        }
    }
}
