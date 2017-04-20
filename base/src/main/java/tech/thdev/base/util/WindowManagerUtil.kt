package tech.thdev.base.util

import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * Created by tae-hwan on 8/22/16.
 */
fun WindowManager.addWindowView(view: View?, xPosition: Int, yPosition: Int, alpha: Float = 1f): WindowManager.LayoutParams? {
    this.let {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.x = xPosition
        layoutParams.y = yPosition
        layoutParams.alpha = alpha
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        layoutParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        layoutParams.format = PixelFormat.TRANSLUCENT
        layoutParams.gravity = Gravity.TOP or Gravity.LEFT

        this.addView(view, layoutParams)

        return layoutParams
    }
}