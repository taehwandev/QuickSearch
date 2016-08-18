package com.anlooper.quicklaunch.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by tae-hwan on 8/17/16.
 *
 * WindowManager app control.
 */
class QuickLaunchService : Service() {

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }
}