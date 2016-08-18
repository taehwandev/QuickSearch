package com.anlooper.quicklaunch.view.window

import com.anlooper.quicklaunch.view.window.presenter.WindowViewContract

/**
 * Created by Tae-hwan on 8/18/16.
 */
class WindowView : WindowViewContract.View {

    private var presenter: WindowViewContract.Presenter? = null

    override fun onPresenter(presenter: WindowViewContract.Presenter) {
        this.presenter = presenter
    }
}

