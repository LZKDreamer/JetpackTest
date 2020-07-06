package com.lzk.jetpacktest.lifecycle

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lzk.jetpacktest.R

/**
 * Activity没有实现LifecycleOwner接口
 */
class CusLifeCycleActivity : Activity(),LifecycleOwner {

    private lateinit var mLifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_life_cycle)
        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.addObserver(MyObserver())
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
}