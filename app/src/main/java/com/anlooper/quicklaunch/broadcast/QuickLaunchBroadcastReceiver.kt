package com.anlooper.quicklaunch.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import com.anlooper.quicklaunch.constant.IntentConstant
import com.anlooper.quicklaunch.constant.PreferenceConstant
import com.anlooper.quicklaunch.service.QuickLaunchService
import tech.thdev.base.util.startServiceClass
import tech.thdev.base.util.stopServiceClass

/**
 * Created by Tae-hwan on 8/18/16.
 */
class QuickLaunchBroadcastReceiver : BroadcastReceiver() {

    var sample: Sample? = null

    fun invokeStuff(action: (Sample.() -> Unit)) {
        this.sample?.test()
    }

    override fun onReceive(p0: Context?, p1: Intent?) {

        p1?.let {
            Log.i("TAG", "onReceive ${it.action}")

            when (it.action) {
                Intent.ACTION_USER_PRESENT -> {
                    // hide icon view
                    sample?.test()
                }
                Intent.ACTION_SCREEN_OFF -> {
                    // show icon view
                    sample?.test()
                }
                Intent.ACTION_BOOT_COMPLETED -> {
                    changeLaunchService(p0)
                }
                IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS -> {
                    changeLaunchService(p0)
                }
                else -> {

                }
            }
        }
    }

    private fun changeLaunchService(context: Context?) {
        context?.let {
            if (PreferenceManager.getDefaultSharedPreferences(it).getBoolean(PreferenceConstant.KEY_PREF_QUICK_LAUNCH_SWITCH, false)) {
                Log.i("TAG", "QuickLaunch start service")
                it.startServiceClass(QuickLaunchService::class.java)
            } else {
                Log.i("TAG", "QuickLaunch stop service")
                it.stopServiceClass(QuickLaunchService::class.java)
            }
        }
    }

    interface Sample {
        fun test()
    }
}