package com.anlooper.quicklaunch.view.setting

import android.os.Bundle
import android.preference.PreferenceFragment
import com.anlooper.quicklaunch.R

/**
 * Created by tae-hwan on 8/17/16.
 */
class MainSettingFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_main_setting)
    }
}