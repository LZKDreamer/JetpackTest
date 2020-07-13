package com.lzk.jetpacktest

import android.app.Application
import android.content.Context

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/12
 * Function:
 */
class MainApp : Application() {

    companion object{
        private var mInstance: MainApp? = null
        private lateinit var mContext: Context
        fun getInstance(): MainApp{
            return if (mInstance == null){
                MainApp()
            }else{
                mInstance!!
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    fun getContext(): Context{
        return mContext
    }
}