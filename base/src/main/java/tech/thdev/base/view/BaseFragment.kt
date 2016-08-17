package tech.thdev.base.view

import android.support.v4.app.Fragment
import tech.thdev.base.presenter.BasePresenter
import tech.thdev.base.presenter.BaseView

/**
 * Created by tae-hwan on 8/17/16.
 */
abstract class BaseFragment<P : BasePresenter<*>>() : Fragment(), BaseView<P> {

    protected var presenter: P? = null
        private set

    override fun onPresenter(presenter: P) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
    }
}