package com.wjd.jetpacklearn.viewmodel.multi_fragment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.wjd.jetpacklearn.R
import com.wjd.jetpacklearn.databinding.ActivityViewModelFragmentBinding

/**
 * viewModel，单Activity多Fragment之间通过viewModel传递数据
 */
class ViewModelFragmentActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityViewModelFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewModelFragmentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        addFragment()
    }

    private fun addFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment1 = ViewModelFragment.newInstance(ViewModelFragment.TYPE_SENDER)
        val fragment2 = ViewModelFragment.newInstance(ViewModelFragment.TYPE_RECEIVER)
        transaction.add(viewBinding.container.id, fragment1, "fragment1")
        transaction.add(viewBinding.container.id, fragment2, "fragment2")
        transaction.hide(fragment2)
        transaction.show(fragment1)
        transaction.commit()

        viewBinding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment1 -> showFragment("fragment1")
                R.id.fragment2 -> showFragment("fragment2")
            }
            true
        }
    }

    private fun showFragment(tag: String) {
        val supportFragmentManager = supportFragmentManager
        val transaction = supportFragmentManager.beginTransaction()
        for (fragment in supportFragmentManager.fragments) {
            if (tag == fragment.tag) {
                transaction.show(fragment)
            } else {
                transaction.hide(fragment)
            }
        }
        transaction.commit()
    }
}