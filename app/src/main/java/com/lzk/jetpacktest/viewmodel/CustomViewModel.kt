package com.lzk.jetpacktest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/11 11:32
 * Description:
 */
class CustomViewModel(private val param: String) : ViewModel(){
    fun printParam(){
        Log.d("CustomViewModel","param:${param}")
    }
}

class MyViewModelFactory(private val name: String) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CustomViewModel(param = name) as T
    }
}