package com.lzk.jetpacktest.api

import com.lzk.jetpacktest.api.bean.HomeArticle
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

    @GET("/article/list/{page}/json")
    fun getHomeArticleList(@Path("page") page: Int): Single<HomeArticle>

}