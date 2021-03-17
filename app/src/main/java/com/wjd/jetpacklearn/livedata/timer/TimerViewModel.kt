package com.wjd.jetpacklearn.livedata.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TimerViewModel : ViewModel() {

    val timeLiveData: MutableLiveData<Long> by lazy { MutableLiveData<Long>(0) }

    private var timer: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }

    fun startTimer() {
        if (timer?.isDisposed == false) {
            return
        }
        timer = Observable.interval(1L, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                timeLiveData.postValue(it)
            }, {})
    }

    private fun stopTimer() {
        if (timer?.isDisposed == false) {
            timer!!.dispose()
        }
    }


}