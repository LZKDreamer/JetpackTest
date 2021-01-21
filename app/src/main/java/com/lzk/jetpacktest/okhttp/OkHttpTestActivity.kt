package com.lzk.jetpacktest.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_ok_http_test.*

class OkHttpTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http_test)
        request()
    }

    private fun request(){
        val mOkHttpApi: IHttpApi = OkHttpApi()
        val params: Map<String,Int> = mapOf(
            "module_id" to 1
        )
        val path = "allocation/component/list"
        mOkHttpApi.get(params,path,object : IHttpCallback{
            override fun onSuccess(data: Any?) {
                val response = data as String
                runOnUiThread {
                    Log.d("TAG","onSuccess:${response}")
                    ok_response_tv.text = response
                }
            }

            override fun onFailed(error: Any?) {
                val msg = error as String
                runOnUiThread {
                    Log.d("TAG","onFailed:${msg}")
                    ok_response_tv.text = msg
                }
            }

        })
    }
}