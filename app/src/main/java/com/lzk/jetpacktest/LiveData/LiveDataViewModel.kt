package com.lzk.jetpacktest.LiveData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

/**
 * Author: LiaoZhongKai
 * Date: 2020/7/7 16:15
 * Description:
 */
class LiveDataViewModel(application: Application) : AndroidViewModel(application) {

    val nameLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val ageLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val mapLiveData: LiveData<Boolean> = Transformations.map(nameLiveData){
        it == "小王"
    }

    val switchMapLiveData: LiveData<String> = Transformations.switchMap(ageLiveData){
        val newLiveData = MutableLiveData<String>()
        newLiveData.value = "switch map ${it}岁"
        return@switchMap newLiveData
    }


}