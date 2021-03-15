package com.wjd.jetpacklearn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wjd.jetpacklearn.databinding.ActivityMainBinding
import com.wjd.jetpacklearn.viewbinding.ViewBindingActivity
import com.wjd.jetpacklearn.viewmodel.multi_fragment.ViewModelFragmentActivity
import com.wjd.jetpacklearn.viewmodel.simple.ViewModelActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnViewBinding.setOnClickListener(this)
        binding.btnViewModel.setOnClickListener(this)
        binding.btnViewModelFragment.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.btnViewBinding.id -> startActivity(ViewBindingActivity::class.java)
            binding.btnViewModel.id -> startActivity(ViewModelActivity::class.java)
            binding.btnViewModelFragment.id -> startActivity(ViewModelFragmentActivity::class.java)
        }
    }

    private fun startActivity(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }
}