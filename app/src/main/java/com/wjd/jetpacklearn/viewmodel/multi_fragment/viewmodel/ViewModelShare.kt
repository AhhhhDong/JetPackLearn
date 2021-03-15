package com.wjd.jetpacklearn.viewmodel.multi_fragment.viewmodel

import androidx.lifecycle.ViewModel

/**
 * 用于尝试同activity两个fragment之间传递数据
 */
class ViewModelShare : ViewModel() {

    /**
     * 点击次数
     */
    var count: Int = 0

}