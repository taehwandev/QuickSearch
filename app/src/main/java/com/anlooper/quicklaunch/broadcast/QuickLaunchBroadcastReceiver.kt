package com.anlooper.quicklaunch.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.anlooper.quicklaunch.data.IntentData

/**
 * Created by Tae-hwan on 8/18/16.
 */
class QuickLaunchBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        p1?.let {
            when (it.action) {
                Intent.ACTION_USER_PRESENT -> {
                    IntentData.intent?.let { p0?.startActivity(it) }
                }
                Intent.ACTION_SCREEN_OFF -> {

                }
                else -> {

                }
            }
        }
    }
}