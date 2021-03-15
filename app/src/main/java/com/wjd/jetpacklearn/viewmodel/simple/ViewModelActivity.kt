package com.wjd.jetpacklearn.viewmodel.simple

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wjd.jetpacklearn.databinding.ActivityTimerBinding

/**
 * ViewModel
 * 这里在ViewModel中实现了一个计时器
 * 并试验了在横竖屏切换中，页面销毁，重新调用了 @see[onCreate] 方法时，创建的ViewModel始终是第一次创建的那个
 * 说明ViewModel的生命周期是超越整个Activity生命周期
 */
class ViewModelActivity : AppCompatActivity(), TestViewModel.View {

    var viewBinding: ActivityTimerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(viewBinding!!.root)
        /*
        需要implementation androidx.activity:activity-ktx
        或不使用ktx: val viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
         */
        val viewModel: TestViewModel by viewModels()
        viewModel.view = this
        viewModel.startTimer()
    }

    @SuppressLint("SetTextI18n")
    override fun showTime(time: Long) {
        viewBinding?.tvTimer?.text = "${time}S"
    }

}