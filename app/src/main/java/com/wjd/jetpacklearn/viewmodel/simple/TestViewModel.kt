package com.wjd.jetpacklearn.viewmodel.simple

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TestViewModel : ViewModel() {

    var view: View? = null

    private var disposable: Disposable? = null

    /**
     * 此方法会在Activity被销毁时调用
     */
    override fun onCleared() {
        super.onCleared()
        view = null
        stopTimer()
    }

    /**
     * 一个计时器，每隔一秒界面展示一次时间
     */
    fun startTimer() {
        /*
         * 这里确保只创建一个计时器，测试当切换横竖屏时，是否回调会一直走
         * 若一直走，说明在横竖屏切换中，activity始终创建的只有一个ViewModel，而不是创建多个
         */
        if (disposable?.isDisposed == false) {
            return
        }
        disposable = Observable.interval(1L, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showTime(it)
            }, { it.printStackTrace() })
    }

    private fun stopTimer() {
        if (disposable?.isDisposed == false) {
            disposable!!.dispose()
        }
    }

    /**
     * Activity实现此接口
     */
    interface View {
        fun showTime(time: Long)
    }
}