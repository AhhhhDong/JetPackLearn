package com.wjd.jetpacklearn.livedata.multi_fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wjd.jetpacklearn.databinding.ActivityMultiFragmentBinding

class MultiFragmentActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMultiFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMultiFragmentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        addFragment()
    }

    private fun addFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        viewBinding.navigation.menu.run {
            val fragment1 = LiveDataShareFragment.newInstance(LiveDataShareFragment.TYPE_SENDER)
            val fragment2 = LiveDataShareFragment.newInstance(LiveDataShareFragment.TYPE_RECEIVER)
            transaction.add(
                viewBinding.container.id,
                fragment1,
                getItem(0).title.toString()
            )
            transaction.add(
                viewBinding.container.id,
                fragment2,
                getItem(1).title.toString()
            )
            transaction.show(fragment1)
            transaction.hide(fragment2)
        }
        transaction.commit()

        viewBinding.navigation.setOnNavigationItemSelectedListener {
            showFragment(it.title.toString())
            true
        }
    }

    private fun showFragment(tag: String) {
        supportFragmentManager.run {
            val transaction = beginTransaction()
            for (fragment in fragments) {
                if (fragment.tag == tag) {
                    transaction.show(fragment)
                } else {
                    transaction.hide(fragment)
                }
            }
            transaction.commit()
        }
    }
}