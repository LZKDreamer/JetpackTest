package com.lzk.jetpacktest.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lzk.jetpacktest.R

/**
 * Author: LiaoZhongKai
 * Date: 2020/8/6 14:35
 * Description:
 */
class GirlLoadStateAdapter(private val retryCallback: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent,retry = retryCallback)
    }
}

class LoadStateViewHolder(parent: ViewGroup,private val retry: ()->Unit,view: View =
    LayoutInflater.from(parent.context).inflate(R.layout.layout_load_state,parent,false))
    : RecyclerView.ViewHolder(view){

    val loadingLl = itemView.findViewById<LinearLayout>(R.id.load_state_loading_ll)
    val errorLl = itemView.findViewById<LinearLayout>(R.id.load_state_error_ll).apply {
        setOnClickListener { retry.invoke() }
    }

    fun bindTo(loadState: LoadState){
        when(loadState){
            is LoadState.Error -> {
                loadingLl.visibility = View.GONE
                errorLl.visibility = View.VISIBLE
            }

            is LoadState.Loading -> {
                loadingLl.visibility = View.VISIBLE
                errorLl.visibility = View.GONE
            }

            is LoadState.NotLoading -> {
                errorLl.visibility = View.GONE
                loadingLl.visibility = View.GONE
            }
        }
    }
}