package com.ani.batch.service

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadImagesWorker(
    private val ctx : Context,
    private val params : WorkerParameters
) : Worker(ctx, params) {

    override fun doWork(): Result {
        for (i in 1..100) {
            Log.i("@ani", "$i")
        }
        return Result.success()
    }
}