package com.lzk.jetpacktest.paging

import androidx.paging.PagingSource
import com.lzk.jetpacktest.api.HttpRepository
import com.lzk.jetpacktest.api.bean.GirlDetail
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/6 14:17
 * Description:
 */
class GirlDataSource : PagingSource<Int,GirlDetail>() {

    private val mHttpRepository: HttpRepository by lazy {
        HttpRepository()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GirlDetail> {
        return try {
            val page = params.key?:1
            val result = mHttpRepository.requestGirlList(page)
            LoadResult.Page(
                data = result.data,
                prevKey = null,
                nextKey = if (result.page == result.page_count) null else page+1
            )
        }catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}