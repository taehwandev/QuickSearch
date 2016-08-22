package com.anlooper.quicklaunch.view.setting

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.util.Log
import com.anlooper.quicklaunch.R
import com.anlooper.quicklaunch.broadcast.QuickLaunchBroadcastReceiver
import com.anlooper.quicklaunch.constant.IntentConstant

/**
 * Created by tae-hwan on 8/17/16.
 */
class MainSettingFragment : PreferenceFragment() {

    private val quickLaunchBroadcastReceiver = QuickLaunchBroadcastReceiver()

    private val swLaunch by lazy {
        findPreference("quick_launch_on_off") as SwitchPreference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main_setting)

        swLaunch.setOnPreferenceChangeListener { preference, any ->
            when (preference) {
                swLaunch -> {
                    Log.d("TAG", "any : $any preference : $preference")
                    quickLaunchStart(any as Boolean)
                }
            }
            true
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(IntentConstant.ACTION_QUICK_LAUNCH_START_SERVICE)
        intentFilter.addAction(IntentConstant.ACTION_QUICK_LAUNCH_STOP_SERVICE)
        activity.registerReceiver(quickLaunchBroadcastReceiver, intentFilter)
    }

    private fun quickLaunchStart(any: Boolean) {
        if (any) {
            startBroadcastReceiver(IntentConstant.ACTION_QUICK_LAUNCH_START_SERVICE)
        } else {
            startBroadcastReceiver(IntentConstant.ACTION_QUICK_LAUNCH_STOP_SERVICE)
        }
    }

    private fun startBroadcastReceiver(type: String) {
        val intent = Intent(type)
        activity.sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        quickLaunchBroadcastReceiver?.let {
            activity.unregisterReceiver(it)
        }
    }
}