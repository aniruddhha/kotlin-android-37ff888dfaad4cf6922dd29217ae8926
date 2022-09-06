package com.ani.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           Msgs(msgs = listOf(
               Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
               Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
               Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
           ))
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

data class Msg(
    val from : String,
    val msg : String,
    val dt : String
)

@Composable
fun MsgItm(msg : Msg) {
    Card(
    ) {
        Row( modifier = Modifier.clickable {
//            AlertDialog(
//                onDismissRequest = {  },
//                title = { Text(text = "Title") },
//                confirmButton = { },
//                dismissButton = { }
//            )
        }) {
            Column {
                Text(text = msg.from)
                Text(text = msg.msg)
            }
            Column {
                Text(text = msg.dt)
                Image(
                    painter = painterResource(R.drawable.ic_android_black_24dp ),
                    contentDescription = "android logo"
                )
            }
        }
    }
}

@Composable
fun Msgs(msgs : List<Msg> ) {

//    Column {
//        msgs.forEach {  msg -> MsgItm(msg = msg) }
//    }

    LazyColumn {
        items(msgs) { msg ->
            MsgItm(msg = msg)
        }
    }
}

//@Preview
//@Composable
//fun AppPrv() {
//    Msgs(msgs = listOf(
//        Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
//        Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
//        Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
//    ))
//}

