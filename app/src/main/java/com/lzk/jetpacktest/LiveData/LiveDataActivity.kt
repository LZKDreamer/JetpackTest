package com.lzk.jetpacktest.LiveData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    private val mViewModel: LiveDataViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        ).get(LiveDataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        mViewModel.mapLiveData.observe(this, Observer {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        })

        mViewModel.switchMapLiveData.observe(this, Observer {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        })

        initEvent()
    }

    private fun initEvent(){
        live_data_map_btn.setOnClickListener {
            mViewModel.nameLiveData.value = "小王"
        }

        live_data_switch_map_btn.setOnClickListener {
            mViewModel.ageLiveData.value = 23
        }
    }
}