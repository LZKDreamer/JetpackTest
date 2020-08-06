package com.lzk.jetpacktest.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/6 14:21
 * Description:
 */
class GirlViewModel : ViewModel() {

    val source: GirlDataSource by lazy {
        GirlDataSource()
    }

    fun getGirls() = Pager(PagingConfig(pageSize = 10)){source}.flow.cachedIn(viewModelScope)
}