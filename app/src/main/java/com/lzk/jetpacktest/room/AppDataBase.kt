package com.lzk.jetpacktest.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/7
 * Function:
 */
@Database(entities = arrayOf(User::class),version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUerDao(): UserDao
}