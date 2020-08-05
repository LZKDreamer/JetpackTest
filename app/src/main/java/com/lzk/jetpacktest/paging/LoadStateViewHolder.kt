package com.lzk.jetpacktest.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.layout_loading_state.view.*

/**
 * Author: LiaoZhongKai.
 * Date: 2020/8/4
 * Function:
 */
class LoadStateViewHolder(parent: ViewGroup, val retryCallback: () -> Unit,
                          private val view: View = LayoutInflater.from(parent.context)
                              .inflate(R.layout.layout_loading_state,parent,false))
    : RecyclerView.ViewHolder(view) {
    init {
        view.loading_retry_btn.setOnClickListener {
            Log.d("TAG","点击重试按钮")
            retryCallback.invoke()
        }
    }

    fun bind(loadState: LoadState){
        view.loading_pb.isVisible = loadState !is LoadState.Error
        view.loading_tv.isVisible =loadState !is LoadState.Error
        view.loading_retry_btn.isVisible = loadState is LoadState.Error
        view.loading_err_tv.isVisible = loadState is LoadState.Error
    }
}