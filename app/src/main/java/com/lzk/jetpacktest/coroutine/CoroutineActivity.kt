package com.lzk.jetpacktest.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_coroutine.*
import kotlinx.coroutines.delay
import java.util.*

class CoroutineActivity : AppCompatActivity() {

    private val mViewModel: CoroutineViewModel by lazy {
        ViewModelProvider(this).get(CoroutineViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        coroutine_btn.setOnClickListener {
            requestGirlInfo()
        }

    }

    private fun requestGirlInfo(){
        lifecycleScope.launchWhenResumed {
            val response = mViewModel.requestGirlInfo()
            if (response.data.isNullOrEmpty()){
                Toast.makeText(this@CoroutineActivity,"数据获取失败",Toast.LENGTH_SHORT).show()
            }else{
                val random = Random()
                val index = random.nextInt(response.data.size-1)
                coroutine_tv.text = response.data[index].desc
            }
        }
    }

}