package tech.thdev.base.util

import android.app.Fragment
import android.preference.PreferenceActivity
import android.support.annotation.IdRes

/**
 * Created by tae-hwan on 8/17/16.
 */
fun PreferenceActivity.setContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    fragmentManager?.beginTransaction()?.replace(frameId, fragment)?.commit()
}