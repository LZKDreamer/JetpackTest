package com.lzk.jetpacktest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lzk.jetpacktest.MainApp

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/7
 * Function:
 */
@Database(entities = [User::class],version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object{
        private var mInstance: AppDataBase? = null

        @Synchronized
        fun getInstance(): AppDataBase{
            return if (mInstance == null){
                Room.databaseBuilder(MainApp.getInstance().getContext(),AppDataBase::class.java,"room_database").allowMainThreadQueries().build()
            }else{
                mInstance!!
            }
        }
    }

    abstract fun getUerDao(): UserDao
}