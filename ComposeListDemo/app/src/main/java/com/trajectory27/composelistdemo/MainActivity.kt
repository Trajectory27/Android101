package com.trajectory27.composelistdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            var color by remember {
//                mutableStateOf(Color.Red)
//            }
//
//            Column {
//                ColorBox(modifier = Modifier.weight(1f)) {
//                    color = it
//                }
//
//                Box(
//                    modifier = Modifier
//                        .background(color)
//                        .weight(1f)
//                        .fillMaxSize()
//                )
//            }
            Scaffold()
        }
    }

}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(0.7f)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )

            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }

}

@Preview(heightDp = 320, widthDp = 320)
@Composable
fun Preview() {
    ImageCard(
        painterResource(R.drawable.my_cute_cat),
        "my cute cat",
        "My cute cat with me"
    )
//    ImageCard(
//        painterResource(R.drawable.girls_open_legs),
//        "girl opens legs",
//        "Girl opens her legs"
//    )
}

@Preview(heightDp = 320, widthDp = 320)
@Composable
fun StylingText() {

    val fontFamily = FontFamily(
        Font(R.font.lora_bold, FontWeight.W400),
        Font(R.font.lora_italic, FontWeight.W500),
        Font(R.font.lora_regular, FontWeight.W600),
        Font(R.font.lora_semibold_italic, FontWeight.W700)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontSize = 50.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("N")
                    }
                    append("ike")
                },
                color = Color.White,
                fontSize = 50.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = "Just do it",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400
            )
        }
    }
}

@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .fillMaxSize()
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            }
    )
}

@Preview(showBackground = true)
@Composable
fun Scaffold() {

    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            TextField(
                value = textFieldState,
                modifier = Modifier
                    .fillMaxWidth(),
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Button(

                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                    }

                }
            ) {
                Text(text = "Greetings!")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumn() {
    LazyColumn {
        itemsIndexed(
            listOf("hi", "Lazy", "Column")
        ) { index, item ->
            Text(
                text = item,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)

            )
        }
    }
}

