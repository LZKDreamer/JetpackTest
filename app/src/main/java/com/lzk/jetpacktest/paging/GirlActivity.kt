package com.lzk.jetpacktest.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_girl.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GirlActivity : AppCompatActivity() {

    private val mViewmodel: GirlViewModel by lazy {
        ViewModelProvider(this).get(GirlViewModel::class.java)
    }

    private val mAdapter: GirlPagingAdapter by lazy {
        GirlPagingAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_girl)

        girl_rv.apply {
            layoutManager = LinearLayoutManager(this@GirlActivity)
            adapter = mAdapter.withLoadStateFooter(GirlLoadStateAdapter{mAdapter.retry()})
        }

        lifecycleScope.launchWhenCreated {
            mViewmodel.getGirls().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}