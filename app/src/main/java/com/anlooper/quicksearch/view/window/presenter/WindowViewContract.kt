package com.anlooper.quicksearch.view.window.presenter

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.MotionEvent
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by Tae-hwan on 8/18/16.
 */
interface WindowViewContract {

    interface View : BaseView<Presenter> {
        fun updateViewLayout(x: Int, y: Int)

        /**
         * User overlay permission change
         */
        @TargetApi(Build.VERSION_CODES.M)
        fun onObtainingPermissionOverlayWindow()

        /**
         * Overlay start
         */
        fun onStartOverlay()
    }

    interface Presenter : BasePresenter<View> {

        fun startOverlayWindowService(context: Context)

        fun onTouch(motionEvent: MotionEvent?): Boolean

        fun destroy()
    }
}