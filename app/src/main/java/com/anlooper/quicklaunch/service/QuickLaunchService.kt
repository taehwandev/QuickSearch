package com.anlooper.quicklaunch.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.anlooper.quicklaunch.broadcast.QuickLaunchBroadcastReceiver
import com.anlooper.quicklaunch.service.listener.QuickLaunchBRListener
import com.anlooper.quicklaunch.view.window.WindowView
import com.anlooper.quicklaunch.view.window.presenter.WindowViewPresenter
import tech.thdev.base.util.registerReceiverAction
import tech.thdev.base.util.startServiceClass

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

        startForeground()
        startServiceClass(SampleService::class.java)

        registerReceiverAction(quickLaunchBroadcastReceiver, listOf(Intent.ACTION_SCREEN_OFF, Intent.ACTION_USER_PRESENT))

        val windowView = WindowView(this)
        WindowViewPresenter().attachView(windowView)

        quickLaunchBroadcastReceiver.brListener = object : QuickLaunchBRListener {
            override fun actionUpdate(action: String?) {
                action?.let {
                    when (it) {
                        Intent.ACTION_USER_PRESENT -> {
                            windowView.hideWindowView()
                        }
                        Intent.ACTION_SCREEN_OFF -> {
                            windowView.showWindowView()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy service")
        unregisterReceiver(quickLaunchBroadcastReceiver)
        windowView?.onDestroy()
    }


}