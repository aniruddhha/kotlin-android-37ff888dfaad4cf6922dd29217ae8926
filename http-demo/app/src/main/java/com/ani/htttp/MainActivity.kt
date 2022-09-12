package com.ani.htttp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val fcmReceiver = object: BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("@ani", "Activity : Data ${intent?.extras?.getString("body")}")
            setFcmMessage( intent?.extras?.getString("body") ?: "default")
        }
    }

    private fun http() {
        val api =  RetrofitBuilder.api
        val findAll : Call<AppResponse> = api.findOne(2)

//        val appResponse = res.execute()

        findAll.enqueue(object  : Callback<AppResponse> {
            override fun onResponse(call: Call<AppResponse>, response: Response<AppResponse>) {

                Log.i("@ani", "#####")
                Log.i("@ani", "Got Response")

                Log.i("@ani", ""+response.body()?.data)
                Log.i("@ani", ""+response.body()?.support)
            }

            override fun onFailure(call: Call<AppResponse>, t: Throwable) {
                Log.i("@ani", "#####")
                Log.i("@ani", "Problem In Getting Reponse")
            }
        })
        // line

        api.createNewUser(
            AppUser(
                "eve.holt@reqres.in",
                "pistol"
            )
        ).enqueue(object : Callback<ResponseCreateUser> {
            override fun onResponse(
                call: Call<ResponseCreateUser>,
                response: Response<ResponseCreateUser>
            ) {
                Log.i("@ani", " Token : "+response.body()?.token)
            }

            override fun onFailure(call: Call<ResponseCreateUser>, t: Throwable) {
                Log.e("@ani", t.message ?: "There is an error")
            }
        })
    }

    private fun fcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("ani", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.i("@ani", "Token : $token")
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fcm()

        FcmEventBus.scp.launch {
            FcmEventBus.bus.collect {
                Log.i("@ani", " Flow : $it")

                setFcmMessage(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                fcmReceiver,
                IntentFilter("com.ani.fcm")
            )
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(fcmReceiver)
        super.onStop()
    }

    fun setFcmMessage(msg : String) {
        findViewById<TextView>(R.id.txt).apply {
            text = msg
        }
    }
}