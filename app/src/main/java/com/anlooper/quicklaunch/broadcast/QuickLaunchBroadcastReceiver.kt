package com.anlooper.quicklaunch.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.anlooper.quicklaunch.constant.IntentConstant
import com.anlooper.quicklaunch.data.IntentData
import com.anlooper.quicklaunch.service.QuickLaunchService

/**
 * Created by Tae-hwan on 8/18/16.
 */
class QuickLaunchBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        p1?.let {
            Log.i("TAG", "onReceive ${it.action}")

            when (it.action) {
                Intent.ACTION_USER_PRESENT -> {
                    IntentData.intent?.let { p0?.startActivity(it) }
                }
                Intent.ACTION_SCREEN_OFF -> {

                }
                Intent.ACTION_BOOT_COMPLETED -> {

                }
                IntentConstant.ACTION_QUICK_LAUNCH_START_SERVICE -> {
                    Log.e("TAG", "startService")
                    startService(p0)
                }
                IntentConstant.ACTION_QUICK_LAUNCH_STOP_SERVICE -> {
                    Log.e("TAG", "stopService")
                    stopService(p0)
                }
                else -> {

                }
            }
        }
    }

    private fun startService(context: Context?) {
        context?.let {
            val intent = Intent(context, QuickLaunchService::class.java)
            it.startService(intent)
        }
    }

    private fun stopService(context: Context?) {
        context?.let {
            val intent = Intent(context, QuickLaunchService::class.java)
            it.stopService(intent)
        }
    }
}