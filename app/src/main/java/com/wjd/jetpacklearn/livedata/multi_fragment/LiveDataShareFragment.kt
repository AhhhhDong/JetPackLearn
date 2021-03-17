package com.wjd.jetpacklearn.livedata.multi_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wjd.jetpacklearn.databinding.FragmentViewModelBinding
import com.wjd.jetpacklearn.livedata.multi_fragment.viewmodel.ViewModelShare
import com.wjd.jetpacklearn.livedata.multi_fragment.viewmodel.ViewModelType

class LiveDataShareFragment : Fragment() {

    companion object {
        /**
         * 更改数据的界面
         */
        const val TYPE_SENDER = 1

        /**
         * 接收数据的界面
         */
        const val TYPE_RECEIVER = 2

        fun newInstance(@Type type: Int): LiveDataShareFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = LiveDataShareFragment()
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * 用于本页面存储数据，绑定的是fragment
     */
    var viewModelType: ViewModelType? = null

    /**
     * 绑定fragment所在的Activity，用于fragment间的数据共享
     */
    var viewModelShare: ViewModelShare? = null

    var binding: FragmentViewModelBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewModelBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        //获取type
        val type = arguments?.getInt("type")
        viewModelType = ViewModelProvider(this).get(ViewModelType::class.java)
        viewModelType!!.type = type
        //初始化共享的viewModel，注意是绑定的Activity
        viewModelShare = ViewModelProvider(requireActivity()).get(ViewModelShare::class.java)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val type = viewModelType?.type
        if (type == TYPE_SENDER) {
            //发送方
            binding?.tvMessage?.visibility = View.GONE
            binding?.btnSend?.visibility = View.VISIBLE
            binding?.btnSend?.setOnClickListener {
                viewModelShare?.run {
                    countLiveData.value = (countLiveData.value ?: 0) + 1
                }
            }
        } else if (type == TYPE_RECEIVER) {
            //接收方
            binding?.tvMessage?.visibility = View.VISIBLE
            binding?.btnSend?.visibility = View.GONE
            viewModelShare?.countLiveData?.observe(viewLifecycleOwner, Observer {
                binding?.tvMessage?.text = "点击了${it} 次"
            })
        }
    }

    //由于liveData观察者模式，当value发生改变时，将自行通知被观察者进行数据刷新，所以无需再在页面显示隐藏时手动去获取数据
//    @SuppressLint("SetTextI18n")
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            //隐藏变为显示，从viewModel获取数据
//            if (viewModelType?.type == TYPE_RECEIVER) {
//                binding?.tvMessage?.text = "点击了${viewModelShare?.count} 次"
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModelType = null
        viewModelShare = null
    }

    @MustBeDocumented
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(value = [TYPE_SENDER, TYPE_RECEIVER])
    @Target(AnnotationTarget.VALUE_PARAMETER)
    annotation class Type
}