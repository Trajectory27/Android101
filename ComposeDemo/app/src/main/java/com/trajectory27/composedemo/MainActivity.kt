package com.trajectory27.composedemo

import android.os.Bundle
import android.view.Gravity
import android.webkit.WebSettings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trajectory27.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ArtistCard()
                }
            }
        }
    }
}

@Composable
fun ArtistCard() {
    val imageModifier = Modifier
        .size(200.dp)
    Row() {
        Image(
            painter = painterResource(id = R.drawable.vladimir_lenin),
            contentDescription = "This is a publicity image of the Russian revolutionary leader Vladimir Lenin, taken in 1920.",
            modifier = imageModifier
        )
        Column() {
            Text(
                text = "Vladimir Lenin",
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp
            )
            Text(
                text = "1870-1924",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        ArtistCard()
    }
}