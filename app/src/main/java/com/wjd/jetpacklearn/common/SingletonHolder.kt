package com.wjd.jetpacklearn.common

/**
 * 实现带参数的单例模式
 */
open class SingletonHolder<T, in A>(private val creator: (A) -> T) {

    private var instance: T? = null

    fun getInstance(params: A) =
        instance ?: synchronized(this) {
            instance ?: creator(params).apply {
                instance = this
            }
        }

}