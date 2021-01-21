package com.lzk.jetpacktest.okhttp

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/24 14:44
 * Description:网络请求的统一接口类
 */
interface IHttpApi {

    /**
     * 抽象的http的异步get请求
     */
    fun get(params: Map<String,Any>,path: String,callback: IHttpCallback)

    /**
     * 抽象的http的异步post请求
     */
    fun post(data: Any,path: String,callback: IHttpCallback)

    /**
     * 取消请求
     */
    fun cancelRequest(tag: Any)

    /**
     * 取消所有请求
     */
    fun cancelAllRequest()
}