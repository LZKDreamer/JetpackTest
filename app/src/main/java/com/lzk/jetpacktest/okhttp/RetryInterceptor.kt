package com.lzk.jetpacktest.okhttp

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/24 17:18
 * Description:
 */
class RetryInterceptor(private val maxRetry: Int) : Interceptor{

    private var retryCount: Int = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        while (!response.isSuccessful && retryCount<maxRetry){
            Log.d("RetryInterceptor","retry:$retryCount")
            retryCount++
           response = chain.proceed(request)
        }
        return response
    }
}
