package com.ani.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Msgs(
                msgs = listOf(
                    Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
                    Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
                    Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
                    Msg(from = "abc", msg = "hey hi", dt = "2022-01-01"),
                )
            )
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
            onValueChange = { setNum1(it) },
            label = { Text(text = "Num1") }
        )
        TextField(
            value = num2,
            onValueChange = { setNum2(it) },
            label = { Text(text = "Num2") }
        )
        Button(onClick = {
            setRes(num1.text.toInt() + num2.text.toInt())
        }) {
            Text(text = "Result")
        }
        Text(text = "Result : ${res}")
    }
}

@Composable
fun ShowUi() {

    val (cnt, setCnt) = remember { mutableStateOf(0) }

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
    val from: String,
    val msg: String,
    val dt: String
)

@Composable
fun MsgItm(msg: Msg) {
    val (isSh, setIsSh) = remember { mutableStateOf(false) }

    ConfirmBox(isSh, setIsSh)

    Card {
        Box(
            modifier = Modifier
                .clickable(
                    onClick = {
                        setIsSh(true)
                    }
                )
                .border(3.dp, Color.Black)
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Text(text = msg.from)
                Text(
                    text = msg.msg,
                    fontSize = 25.sp
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Text(text = msg.dt)
                Image(
                    painter = painterResource(R.drawable.ic_android_black_24dp),
                    contentDescription = "android logo",
                )
            }
        }
    }
}

@Composable
fun Msgs(msgs: List<Msg>) {

//    Column {
//        msgs.forEach {  msg -> MsgItm(msg = msg) }
//    }

    LazyColumn {
        items(msgs) { msg ->
            MsgItm(msg = msg)
        }
    }
}

@Composable
fun ConfirmBox(isSh: Boolean, onClose: (sh: Boolean) -> Unit) {
    if (isSh) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text(text = "Title") },
            confirmButton = { },
            dismissButton = {
                TextButton(
                    onClick = { onClose(false) }
                ) {
                    Text(text = "Close")
                }
            }
        )
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

