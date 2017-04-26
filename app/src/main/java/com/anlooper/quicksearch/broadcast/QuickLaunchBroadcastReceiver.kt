package com.anlooper.quicksearch.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import com.anlooper.quicksearch.constant.IntentConstant
import com.anlooper.quicksearch.constant.PreferenceConstant
import com.anlooper.quicksearch.service.QuickLaunchService
import com.anlooper.quicksearch.service.listener.QuickLaunchBRListener
import tech.thdev.base.util.startServiceClass
import tech.thdev.base.util.stopServiceClass

/**
 * Created by Tae-hwan on 8/18/16.
 */
class QuickLaunchBroadcastReceiver : BroadcastReceiver() {

    var brListener: QuickLaunchBRListener? = null

    override fun onReceive(p0: Context?, p1: Intent?) {

        p1?.let {
            Log.i("TAG", "onReceive ${it.action}")
            brListener?.actionUpdate(it.action)

            when (it.action) {
                Intent.ACTION_USER_PRESENT -> {
                    // User present ... event
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
}