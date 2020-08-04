package com.lzk.jetpacktest.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.CombinedLoadStates
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalPagingApi
class PagingActivity : AppCompatActivity() {

    private val mPagingViewModel: PagingViewModel by lazy {
        ViewModelProvider(this).get(PagingViewModel::class.java)
    }

    private val mAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        observe()
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
        paging_rv.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter.withLoadStateFooter(PagingLoadStateAdapter(this@PagingActivity::retry))
        }
        paging_refresh_layout.setOnRefreshListener {
            mAdapter.refresh()
        }
    }

    private fun retry(){
        Log.d("TAG","retry")
        mAdapter.retry()
    }

    private fun observe(){
        mPagingViewModel.mHomeArticleLiveData.observe(this, Observer {
            mAdapter.submitData(lifecycle,it)
        })
    }

    private fun requestHomeArticleData(){
        mPagingViewModel.getHomeArticleData()
    }
}