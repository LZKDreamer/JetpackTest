package com.lzk.jetpacktest.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/4 16:44
 * Description:
 */
class PagingViewModel : ViewModel() {

    /*
    * Flow<PagingData>有一个方便的cachedIn()方法，使我们可以将内容缓存Flow<PagingData>在CoroutineScope中，
    * 如果我们放在viewmodel中，这样我们就可以在配置被更改之后，activity能够接收到原有的数据而不用重新开始获取，
    * 如果你要在flow上用map等操作符的时候，要确保cachedIn()是在最后面的，如果没有缓存，
    * 那么在切换横竖屏的时候就会重新开始请求
    * */
    fun getHomeArticleData() = Pager(PagingConfig(pageSize = 5,prefetchDistance = 2)){
        GirlDataSource()
    }.flow.cachedIn(viewModelScope)
}