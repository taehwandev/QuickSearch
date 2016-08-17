package com.anlooper.quicklaunch.view

import android.os.Bundle
import android.preference.PreferenceManager
import com.anlooper.quicklaunch.AppCompatPreferenceActivity
import com.anlooper.quicklaunch.R
import tech.thdev.base.util.setContentFragment

/**
 * Created by tae-hwan on 8/17/16.
 */
class MainSettingActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentFragment(android.R.id.content, ServicePreferenceFragment())
        PreferenceManager.setDefaultValues(this@MainSettingActivity, R.xml.pref_main_setting, false)
        setupActionBar()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
    }
}

