package com.lzk.jetpacktest.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lzk.jetpacktest.api.HttpRepository
import com.lzk.jetpacktest.api.bean.GirlDetail
import com.lzk.jetpacktest.api.bean.GirlResponse
import kotlinx.coroutines.launch
import java.util.*

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/19 16:51
 * Description:
 */
class CoroutineViewModel : ViewModel() {

    private val mHttpRepository: HttpRepository by lazy {
        HttpRepository()
    }

    private val random = Random()

    suspend fun requestGirlInfo(): GirlResponse{
        val page = random.nextInt(10)
        return mHttpRepository.requestGirlList(page)
    }
}