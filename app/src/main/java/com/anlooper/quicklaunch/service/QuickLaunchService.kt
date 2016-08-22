package com.anlooper.quicklaunch.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import com.anlooper.quicklaunch.broadcast.QuickLaunchBroadcastReceiver

/**
 * Created by tae-hwan on 8/17/16.
 *
 * WindowManager app control.
 */
class QuickLaunchService : Service() {

    private val quickLaunchBroadcastReceiver = QuickLaunchBroadcastReceiver()

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        Log.d("TAG", "onCreate")

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        registerReceiver(quickLaunchBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy service")
        unregisterReceiver(quickLaunchBroadcastReceiver)
    }
}