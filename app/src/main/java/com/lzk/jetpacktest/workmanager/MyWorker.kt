package com.lzk.jetpacktest.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Author: LiaoZhongKai.
 * Date: 2020/8/10
 * Function:
 */
class MyWorker(context: Context,params: WorkerParameters) : Worker(context,params) {
    override fun doWork(): Result {

        val param = inputData.getString("param")
        Log.d("TAG","param参数：${param}")

        Log.d("TAG","doWork()")
        val outData = Data.Builder().putString("result","我是返回结果参数").build()
        return Result.success(outData)
    }
}