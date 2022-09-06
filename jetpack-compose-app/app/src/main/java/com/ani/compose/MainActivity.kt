package com.ani.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           Calculator()
        }
    }
}

@Composable
fun Calculator() {

    val (num1, setNum1) = remember { mutableStateOf(TextFieldValue()) }
    val (num2, setNum2) = remember { mutableStateOf(TextFieldValue()) }
    val (res, setRes) = remember { mutableStateOf(0) }

    Column() {

        TextField(
            value = num1, 
            onValueChange = { setNum1(it)  },  
            label = { Text(text = "Num1") } 
        )
        TextField(
            value = num2,
            onValueChange = { setNum2(it)  } ,
            label = { Text(text = "Num2") }
        )
        Button(onClick = {
            setRes ( num1.text.toInt() + num2.text.toInt() )
        }) {
            Text(text = "Result")
        }
        Text(text = "Result : ${res}")
    }
}

@Composable
fun ShowUi() {

    val ( cnt, setCnt ) = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Screen $cnt",
            fontSize = 30.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {

            setCnt(cnt + 1)

            Log.i("@ani", "Clicked $cnt")
        }) {
            Text(text = "Send OTP")
        }
    }
}

