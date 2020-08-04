package com.lzk.jetpacktest.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.flow.collectLatest

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
            if (it.refresh !is LoadState.Loading){
                paging_refresh_layout.isRefreshing = false
            }
        }
        paging_refresh_layout.setOnRefreshListener {
            mAdapter.refresh()
        }
        paging_rv.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = mAdapter
        }
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