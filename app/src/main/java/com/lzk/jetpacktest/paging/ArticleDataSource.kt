package com.lzk.jetpacktest.paging

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.rxjava2.RxPagingSource
import com.lzk.jetpacktest.api.HttpRepository
import com.lzk.jetpacktest.api.bean.Article
import com.lzk.jetpacktest.api.bean.HomeArticle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 15:44
 * Description:
 */
//表示下一页数据的加载方式，比如使用页码加载可以传Int，使用最后一条数据的某个属性来加载下一页就传别的类型比如String等
class ArticleDataSource() : RxPagingSource<Int,Article>() {

    private val mHttpRepository: HttpRepository by lazy {
        HttpRepository()
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Article>> {
        val page = params.key?:0
        return mHttpRepository.requestWXArticles(page)
            .delay(2,TimeUnit.SECONDS)//延时以便更好的观察加载更多的效果
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{
                val result: LoadResult<Int,Article> = LoadResult.Page(
                    data = it.data.datas,
                    prevKey = if (page== 0) null else page-1,
                    nextKey = if (page >= it.data.pageCount) null else page+1)
                return@map result
                //下面这样写会报错，因为没有指定LoadResult.Page的类型为LoadResult<Int, Article>
//                return@map LoadResult.Page(
//                    data = it.data.datas,
//                    prevKey = if (page== 0) null else page-1,
//                    nextKey = if (page >= it.data.pageCount) null else page+1)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }
}