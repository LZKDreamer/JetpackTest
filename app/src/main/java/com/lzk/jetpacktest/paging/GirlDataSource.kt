package com.lzk.jetpacktest.paging

import android.util.Log
import androidx.paging.PagingSource
import com.lzk.jetpacktest.api.HttpRepository
import com.lzk.jetpacktest.api.bean.GirlDetail
import kotlin.Exception

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 15:44
 * Description:
 */
//表示下一页数据的加载方式，比如使用页码加载可以传Int，使用最后一条数据的某个属性来加载下一页就传别的类型比如String等
class GirlDataSource() : PagingSource<Int,GirlDetail>() {

    private val mHttpRepository: HttpRepository by lazy {
        HttpRepository()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GirlDetail> {
        try {
            val page: Int = params.key?:1
            val result = mHttpRepository.requestGirlList(page)
            return LoadResult.Page(
                data = result.data,
                prevKey = null,
                nextKey = if (page >= result.page_count) null else page+1
            )
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}