package com.lzk.jetpacktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.paging.ExperimentalPagingApi
import com.lzk.jetpacktest.LiveData.LiveDataActivity
import com.lzk.jetpacktest.coroutine.CoroutineActivity
import com.lzk.jetpacktest.databinding.DataBindingActivity
import com.lzk.jetpacktest.lifecycle.LifeCycleActivity
import com.lzk.jetpacktest.navigation.NavigationActivity
import com.lzk.jetpacktest.paging.GirlActivity
import com.lzk.jetpacktest.room.RoomActivity
import com.lzk.jetpacktest.viewmodel.ViewModelActivity
import com.lzk.jetpacktest.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
    }

    private fun initEvent(){
        main_lifecycle_btn.setOnClickListener(this)
        main_cst_lifecycle_btn.setOnClickListener(this)
        main_livedata_btn.setOnClickListener(this)
        main_room_btn.setOnClickListener(this)
        main_navigation_btn.setOnClickListener(this)
        main_paging_btn.setOnClickListener(this)
        main_workmanager_btn.setOnClickListener(this)
        main_coroutine_btn.setOnClickListener(this)
        main_viewmodel_btn.setOnClickListener(this)
        main_databinding_btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.main_lifecycle_btn -> startActivity(Intent(this,LifeCycleActivity::class.java))
            R.id.main_cst_lifecycle_btn -> startActivity(Intent(this,LifeCycleActivity::class.java))
            R.id.main_livedata_btn -> startActivity(Intent(this,LiveDataActivity::class.java))
            R.id.main_room_btn -> startActivity(Intent(this,RoomActivity::class.java))
            R.id.main_navigation_btn -> startActivity(Intent(this,NavigationActivity::class.java))
            R.id.main_paging_btn -> startActivity(Intent(this,GirlActivity::class.java))
            R.id.main_workmanager_btn -> startActivity(Intent(this,WorkManagerActivity::class.java))
            R.id.main_coroutine_btn -> startActivity(Intent(this,CoroutineActivity::class.java))
            R.id.main_viewmodel_btn -> startActivity(Intent(this,ViewModelActivity::class.java))
            R.id.main_databinding_btn -> startActivity(Intent(this,DataBindingActivity::class.java))
        }
    }
}
