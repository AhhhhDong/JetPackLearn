package com.wjd.jetpacklearn.livedata.multi_fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 用于尝试同activity两个fragment之间传递数据
 */
class ViewModelShare : ViewModel() {

    /**
     * 点击次数
     */
    val countLiveData: MutableLiveData<Int> by lazy { MutableLiveData(0) }


}