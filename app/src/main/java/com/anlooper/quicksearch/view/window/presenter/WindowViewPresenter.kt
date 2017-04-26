package com.anlooper.quicksearch.view.window.presenter

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import tech.thdev.base.presenter.AbstractPresenter

/**
 * Created by Tae-hwan on 8/18/16.
 */
class WindowViewPresenter : AbstractPresenter<WindowViewContract.View>(), WindowViewContract.Presenter {

    private var touchPrevX: Float? = 0.0f
    private var touchPrevY: Float? = 0.0f

    override fun attachView(view: WindowViewContract.View) {
        super.attachView(view)

        view.onPresenter(this@WindowViewPresenter)
    }

    override fun startOverlayWindowService(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            view?.onObtainingPermissionOverlayWindow()

        } else {
            view?.onStartOverlay()
        }
    }

    override fun onTouch(motionEvent: MotionEvent?): Boolean {
        when (motionEvent?.action) {
            MotionEvent.ACTION_DOWN -> {
                actionDown(motionEvent)
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                actionMove(motionEvent)
                return false
            }
            MotionEvent.ACTION_UP -> {
                actionUp(motionEvent)
                return false
            }
        }
        return true
    }

    private fun startCount() {

    }

    private fun actionDown(motionEvent: MotionEvent?) {
        touchPrevX = motionEvent?.rawX
        touchPrevY = motionEvent?.rawY
        Log.i("TAG", "actionDown")
    }

    private fun actionMove(motionEvent: MotionEvent?) {
        val rawX = motionEvent?.rawX as Float
        val rawY = motionEvent?.rawY as Float

        val x = getDistance(rawX, touchPrevX!!).toInt()
        val y = getDistance(rawY, touchPrevY!!).toInt()

        touchPrevX = rawX
        touchPrevY = rawY

        view?.updateViewLayout(x, y)
        Log.i("TAG", "actionMove")
    }

    private fun actionUp(motionEvent: MotionEvent?) {
        // Do noting ...
        Log.i("TAG", "actionUp")
    }

    override fun destroy() {
        touchPrevX = 0.0f
        touchPrevY = 0.0f
    }

    private fun getDistance(raw: Float, prev: Float): Float = raw.minus(prev)
}