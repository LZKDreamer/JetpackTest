package com.lzk.jetpacktest.api

import com.lzk.jetpacktest.api.bean.GirlResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 15:04
 * Description:
 */
interface ApiService {

    @GET("data/category/Girl/type/Girl/page/{page}/count/10")
    suspend fun getGirlList(@Path("page") page: Int): GirlResponse


}