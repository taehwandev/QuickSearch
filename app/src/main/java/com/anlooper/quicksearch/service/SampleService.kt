package com.anlooper.quicksearch.service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.v7.app.NotificationCompat
import android.util.Log
import com.anlooper.quicksearch.R

/**
 * Created by Tae-hwan on 8/23/16.
 */
class SampleService : Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        startForeground()
        stopSelf()
        Log.e("TAG", "onCreate and stopSelf")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("TAG", "onDestroy")
        stopForeground(true)
    }
}

fun Service.startForeground() {
    try {
        val notification = getNotification()
        startForeground(1220, notification)
        Log.i("TAG", "startForeground")
    } catch (e: Exception) {

    }
}

fun Context.getNotification(): Notification? {
    var smallIcon = R.mipmap.ic_launcher
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        smallIcon = R.mipmap.ic_launcher
    }
    val notification = NotificationCompat.Builder(this).setSmallIcon(smallIcon).setPriority(NotificationCompat.PRIORITY_MIN).setAutoCancel(true).setWhen(0).setTicker("").build()
    notification.flags = 16
    return notification
}