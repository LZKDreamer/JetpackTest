package com.lzk.jetpacktest.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity() {


    private lateinit var mAppDataBase: AppDataBase
    private lateinit var mUserDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initEvent()
        mAppDataBase = Room.databaseBuilder(this,AppDataBase::class.java,"room_database").allowMainThreadQueries().build()
        mUserDao = mAppDataBase.getUerDao()
    }

    private fun initEvent(){
        add_btn.setOnClickListener {
            mUserDao.addUser(User(1,"狗蛋儿"))
            updateView()
        }

        delete_btn.setOnClickListener {
            mUserDao.deleteUser(User(1,"狗蛋儿"))
            updateView()
        }

        update_btn.setOnClickListener {
            mUserDao.updateUser(User(1,"小狗蛋儿"))
            updateView()
        }

        query_btn.setOnClickListener {
            updateView()
        }
    }

    private fun updateView(){
        val users = mUserDao.getAllUser()
        if (users.isNullOrEmpty()){
            textView.text = "暂无数据"
        }
        users.forEach{
            textView.text = it.username
        }
    }
}