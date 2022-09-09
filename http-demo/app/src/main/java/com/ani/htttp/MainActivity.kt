package com.ani.htttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res : Call<AppResponse> = RetrofitBuilder.api.findOne(2)

//        val appResponse = res.execute()

        res.enqueue(object  : Callback<AppResponse> {
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
    }
}