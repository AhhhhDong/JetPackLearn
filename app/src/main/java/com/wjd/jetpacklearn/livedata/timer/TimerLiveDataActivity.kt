package com.wjd.jetpacklearn.livedata.timer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wjd.jetpacklearn.databinding.ActivityTimerBinding

class TimerLiveDataActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val viewModel: TimerViewModel by viewModels()
//        val viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)


        viewModel.timeLiveData.observe(this, Observer {
            viewBinding.tvTimer.text = "${it}s"
        })

        viewModel.startTimer()
    }
}