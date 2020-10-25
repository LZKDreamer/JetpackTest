package com.lzk.jetpacktest.okhttp

import android.util.Log
import androidx.collection.SimpleArrayMap
import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Author: LiaoZhongKai
 * Date: 2020/10/24 14:58
 * Description:
 */
class OkHttpApi : IHttpApi{

    companion object{
        private const val TAG: String = "OkHttpApi"
    }

    private val mBaseUrl: String = "http://yapi.54yct.com/mock/24/"

    private var mCancelMap: SimpleArrayMap<Any,Call> = SimpleArrayMap()

    private val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)//完整请求超时时长，从发起到接收返回数据，默认值0,不限
        .connectTimeout(10, TimeUnit.SECONDS)//与服务器建立连接的时长，默认10s
        .readTimeout(10, TimeUnit.SECONDS)//读取服务器返回数据的时长
        .writeTimeout(10, TimeUnit.SECONDS)//向服务器写入数据的时长，默认10s
        .cache(Cache(File("sdcard/cache"), 1024))//http 的缓存大小，位置
        .cookieJar(CookieJar.NO_COOKIES)//不使用cookie，若用，自定义cookieJar实现
        .addNetworkInterceptor(RetryInterceptor(3))//自定义重连次数
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()

    override fun get(params: Map<String, Any>, path: String, callback: IHttpCallback) {
        val url = "$mBaseUrl$path"
        val urlBuilder = url.toHttpUrl().newBuilder()
        params.forEach{
            urlBuilder.addEncodedQueryParameter(it.key,it.value.toString())
        }
        val request = Request.Builder()
            .get()
            .tag(params)
            .url(urlBuilder.build())
            .build()

        val call: Call = mOkHttpClient.newCall(request)
        //存放Call对象
        mCancelMap.put(request.tag(),call)

        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string())
            }

        })
    }

    override fun post(data: Any, path: String, callback: IHttpCallback) {
        val url = "$mBaseUrl$path"
        val request = Request.Builder()
            .post(Gson().toJson(data).toRequestBody())
            .tag(data)
            .url(url)
            .build()

        val call: Call = mOkHttpClient.newCall(request)
        //存放Call对象
        mCancelMap.put(request.tag(),call)

        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string())
            }
        })
    }

    override fun cancelRequest(tag: Any) {
        mCancelMap.get(tag)?.cancel()
    }

    override fun cancelAllRequest() {
        for ( i in 0 until mCancelMap.size()){
            mCancelMap.get(mCancelMap.keyAt(i))?.cancel()
        }
    }
}