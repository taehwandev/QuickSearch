package tech.thdev.base.util

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.preference.PreferenceActivity
import android.support.annotation.IdRes

/**
 * Created by tae-hwan on 8/17/16.
 */
fun PreferenceActivity.setContentFragment(@IdRes frameId: Int, fragment: Fragment) {
    fragmentManager?.beginTransaction()?.replace(frameId, fragment)?.commit()
}

fun <E> Context?.startServiceClass(cls: Class<E>?) {
    this?.let {
        val intent = Intent(it, cls)
        it.startService(intent)
    }
}

fun <E> Context?.stopServiceClass(cls: Class<E>?) {
    this?.let {
        val intent = Intent(it, cls)
        it.stopService(intent)
    }
}