package com.lzk.jetpacktest.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.lzk.jetpacktest.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlin.random.Random

class RoomActivity : AppCompatActivity() {


    private lateinit var mAppDataBase: AppDataBase
    private lateinit var mUserDao: UserDao
    private var mId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initEvent()
        mAppDataBase = AppDataBase.getInstance()
        mUserDao = mAppDataBase.getUerDao()
        mId = Random.nextInt(99)
    }

    private fun initEvent(){
        add_btn.setOnClickListener {
            mId = Random.nextInt(99)
            mUserDao.addUser(User(mId, "梨花:$mId",23))
            updateView()
        }

        delete_btn.setOnClickListener {
            mUserDao.deleteUser(User(mId,"梨花",18))
            updateView()
        }

        update_btn.setOnClickListener {
            mUserDao.updateUser(User(mId,"小梨花",22))
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