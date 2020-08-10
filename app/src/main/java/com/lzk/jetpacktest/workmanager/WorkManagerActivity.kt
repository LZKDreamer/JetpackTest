package com.lzk.jetpacktest.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.lzk.jetpacktest.R

class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val request = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints).setInputData(Data.Builder().putString("param","我是参数").build())
            .build()
        WorkManager.getInstance().enqueue(request)

        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, Observer {
            it?.let { workInfo ->
                when(it.state){
                    WorkInfo.State.SUCCEEDED -> {
                        val result = it.outputData.getString("result")
                        Toast.makeText(this,"执行成功${result}",Toast.LENGTH_SHORT).show()
                        Log.d("TAG","后台任务执行成功")
                    }
                    WorkInfo.State.BLOCKED -> {
                        Log.d("TAG","后台任务挂起")
                    }
                    WorkInfo.State.CANCELLED -> {
                        Log.d("TAG","后台任务取消")
                    }
                    WorkInfo.State.FAILED -> {
                        Log.d("TAG","后台任务失败")
                    }
                    WorkInfo.State.ENQUEUED -> {
                        Log.d("TAG","后台任务已运行")
                    }
                    WorkInfo.State.RUNNING -> {
                        Log.d("TAG","后台任务正在执行")
                    }

                }
            }
        })
    }


}