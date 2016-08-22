package com.anlooper.quicklaunch.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.anlooper.quicklaunch.broadcast.QuickLaunchBroadcastReceiver
import com.anlooper.quicklaunch.view.window.WindowView
import com.anlooper.quicklaunch.view.window.presenter.WindowViewPresenter
import tech.thdev.base.util.registerReceiverAction

/**
 * Created by tae-hwan on 8/17/16.
 *
 * WindowManager app control.
 */
class QuickLaunchService : Service() {

    private val quickLaunchBroadcastReceiver = QuickLaunchBroadcastReceiver()

    var windowView: WindowView? = null

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        Log.d("TAG", "onCreate")

        quickLaunchBroadcastReceiver.sample = object : QuickLaunchBroadcastReceiver.Sample{
            override fun test() {
                Log.d("TAG", "abcc")
            }
        }

        registerReceiverAction(quickLaunchBroadcastReceiver, listOf(Intent.ACTION_SCREEN_OFF, Intent.ACTION_USER_PRESENT))

        val windowView = WindowView(this)
        WindowViewPresenter().attachView(windowView)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy service")
        unregisterReceiver(quickLaunchBroadcastReceiver)
        windowView?.onDestroy()
    }
}