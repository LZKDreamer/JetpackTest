package com.lzk.jetpacktest.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lzk.jetpacktest.R

class LifeCycleActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        initLifeCycle()
        Log.d(TAG,"STATE onCreate:${lifecycle.currentState}")
    }

    private fun initLifeCycle(){
        lifecycle.addObserver(MyObserver())
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"STATE onStart:${lifecycle.currentState}")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"STATE onResume:${lifecycle.currentState}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"STATE onPause:${lifecycle.currentState}")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"STATE onStop:${lifecycle.currentState}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"STATE onDestroy:${lifecycle.currentState}")
    }
}