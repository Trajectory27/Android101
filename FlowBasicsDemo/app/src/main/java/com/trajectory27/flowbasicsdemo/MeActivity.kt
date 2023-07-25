package com.trajectory27.flowbasicsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trajectory27.flowbasicsdemo.ui.theme.FlowBasicsDemoTheme

class MeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowBasicsDemoTheme {
                val viewModel = viewModel<MeViewModel>()
                Text(text = viewModel.stringNumber)
            }
        }
    }
}