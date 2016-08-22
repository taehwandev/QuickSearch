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
import com.anlooper.quicklaunch.constant.PreferenceConstant

/**
 * Created by tae-hwan on 8/17/16.
 */
class MainSettingFragment : PreferenceFragment() {

    private val quickLaunchBroadcastReceiver = QuickLaunchBroadcastReceiver()

    private val swLaunch by lazy {
        findPreference(PreferenceConstant.KEY_PREF_QUICK_LAUNCH_SWITCH) as SwitchPreference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main_setting)

        swLaunch.setOnPreferenceChangeListener { preference, any ->
            when (preference) {
                swLaunch -> {
                    Log.d("TAG", "any : $any preference : $preference")
                    startBroadcastReceiver(IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS)
                }
            }
            true
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS)
        activity.registerReceiver(quickLaunchBroadcastReceiver, intentFilter)
    }

    private fun startBroadcastReceiver(type: String) {
        val intent = Intent(type)
        activity.sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity.unregisterReceiver(quickLaunchBroadcastReceiver)
    }
}