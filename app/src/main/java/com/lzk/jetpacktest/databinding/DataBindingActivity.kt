package com.lzk.jetpacktest.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.lzk.jetpacktest.R

class DataBindingActivity : AppCompatActivity() {

    private val mEmployee: Employee = Employee("我是名字")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding)
        binding.apply {
            employee = Employee("我是名字")
            click = View.OnClickListener { desc = if (desc.isNullOrEmpty()) "我是描述" else null }
            age = 23
        }
    }
}