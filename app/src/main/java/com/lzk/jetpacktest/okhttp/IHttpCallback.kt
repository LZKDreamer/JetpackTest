package com.lzk.jetpacktest.okhttp

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/24 14:46
 * Description:网络请求的接口回调
 */
interface IHttpCallback {

    /**
     * 成功回调
     */
    fun onSuccess(data: Any?)

    /**
     * 失败回调
     */
    fun onFailed(error: Any?)
}