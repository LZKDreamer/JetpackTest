package com.lzk.jetpacktest.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzk.jetpacktest.R
import com.lzk.jetpacktest.api.bean.GirlDetail
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class PagingActivity : AppCompatActivity() {

    private val mPagingViewModel: PagingViewModel by lazy {
        ViewModelProvider(this).get(PagingViewModel::class.java)
    }

    private val mAdapter: GirlAdapter by lazy {
        GirlAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        initRV()
        requestHomeArticleData()
    }

    private fun initRV(){
        mAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.Error -> paging_refresh_layout.isRefreshing = false
                is LoadState.Loading -> paging_refresh_layout.isRefreshing = true
                is LoadState.NotLoading -> paging_refresh_layout.isRefreshing = false
            }
        }

        mAdapter.addDataRefreshListener {
            paging_refresh_layout.isRefreshing = false
        }
        mAdapter.setOnItemClickListener(object : GirlAdapter.OnItemClickListener{
            override fun onItemClick(view: View, data: GirlDetail, position: Int) {
                when(view.id){
                    R.id.item_girl_iv -> {
                        Toast.makeText(this@PagingActivity,data.title,Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        paging_rv.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter.withLoadStateFooter(PagingLoadStateAdapter(this@PagingActivity::retry))
        }
        paging_refresh_layout.setOnRefreshListener {
            mAdapter.refresh()
        }
    }

    private fun retry(){
        mAdapter.retry()
    }

    private fun requestHomeArticleData(){
        //订阅Pager数据流，将数据传递给adapter
        lifecycleScope.launch {
            mPagingViewModel.getHomeArticleData().collect {
                mAdapter.submitData(it)
            }
        }
    }
}