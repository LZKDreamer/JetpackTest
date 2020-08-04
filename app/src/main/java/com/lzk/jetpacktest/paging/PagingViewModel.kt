package com.lzk.jetpacktest.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.lzk.jetpacktest.api.bean.Article
import io.reactivex.Flowable
import kotlinx.coroutines.flow.subscribe

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 16:44
 * Description:
 */
class PagingViewModel : ViewModel() {

    val mHomeArticleLiveData: MutableLiveData<PagingData<Article>> by lazy {
        MutableLiveData<PagingData<Article>>()
    }

    fun getHomeArticleData(){
        Pager(PagingConfig(pageSize = 5,prefetchDistance = 2)){
            ArticleDataSource()
        }.flowable.subscribe({
            mHomeArticleLiveData.postValue(it)
        },{
            Log.d("TAG",it.message)
        })
    }
}