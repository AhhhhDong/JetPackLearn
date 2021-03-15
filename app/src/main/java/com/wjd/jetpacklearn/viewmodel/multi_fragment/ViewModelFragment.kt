package com.wjd.jetpacklearn.viewmodel.multi_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wjd.jetpacklearn.databinding.FragmentViewModelBinding
import com.wjd.jetpacklearn.viewmodel.multi_fragment.viewmodel.ViewModelShare
import com.wjd.jetpacklearn.viewmodel.multi_fragment.viewmodel.ViewModelType

class ViewModelFragment : Fragment() {

    companion object {
        /**
         * 更改数据的界面
         */
        const val TYPE_SENDER = 1

        /**
         * 接收数据的界面
         */
        const val TYPE_RECEIVER = 2

        fun newInstance(@Type type: Int): ViewModelFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = ViewModelFragment()
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

    private fun initView() {
        val type = viewModelType?.type
        if (type == TYPE_SENDER) {
            //发送方
            binding?.tvMessage?.visibility = View.GONE
            binding?.btnSend?.visibility = View.VISIBLE
            binding?.btnSend?.setOnClickListener { viewModelShare?.let { it.count++ } }
        } else if (type == TYPE_RECEIVER) {
            //接收方
            binding?.tvMessage?.visibility = View.VISIBLE
            binding?.btnSend?.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            //隐藏变为显示，从viewModel获取数据
            if (viewModelType?.type == TYPE_RECEIVER) {
                binding?.tvMessage?.text = "点击了${viewModelShare?.count} 次"
            }
        }
    }

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