package com.wjd.jetpacklearn.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wjd.jetpacklearn.R
import com.wjd.jetpacklearn.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.tvContent.text = getString(R.string.viewBindingExample)
    }
}