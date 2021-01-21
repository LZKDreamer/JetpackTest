package com.lzk.jetpacktest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.lzk.jetpacktest.R

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/11 10:38
 * Description:
 */
class ViewModelOne(application: Application) : AndroidViewModel(application) {
    fun method(){
        //获取Application
        Log.d("ViewModelOne","ViewModelOne:"+getApplication<Application>().resources.getString(R.string.app_name))
    }

    fun printSomething(){
        Log.d("ViewModelOne","ViewModelOne print something")
    }
}

class ViewModelTwo : ViewModel(){
    fun printSomething(tag: String){
        Log.d("ViewModelTwo","ViewModelTwo print something:${tag}")
    }
}