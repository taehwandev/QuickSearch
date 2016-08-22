package com.anlooper.quicklaunch.view.setting

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.provider.Settings
import android.util.Log
import com.anlooper.quicklaunch.R
import com.anlooper.quicklaunch.broadcast.QuickLaunchBroadcastReceiver
import com.anlooper.quicklaunch.constant.IntentConstant
import com.anlooper.quicklaunch.constant.PreferenceConstant
import tech.thdev.base.util.registerReceiverAction

/**
 * Created by tae-hwan on 8/17/16.
 */
class MainSettingFragment : PreferenceFragment() {

    val REQ_CODE_OVERLAY_PERMISSION = 100

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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                            && !Settings.canDrawOverlays(context)) {
                        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.packageName))
                        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION)
                    } else {
                        startBroadcastReceiver(IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS)
                    }
                }
            }
            true
        }

        activity.registerReceiverAction(quickLaunchBroadcastReceiver, listOf(IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_OVERLAY_PERMISSION) {
            startBroadcastReceiver(IntentConstant.ACTION_QUICK_LAUNCH_SERVICE_CHANGE_STATUS)
        }
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