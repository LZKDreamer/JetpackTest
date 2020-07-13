package com.lzk.jetpacktest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lzk.jetpacktest.MainApp

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/7
 * Function:
 */
@Database(entities = [User::class],version = 2)
abstract class AppDataBase : RoomDatabase() {

    companion object{
        private var mInstance: AppDataBase? = null

        @Synchronized
        fun getInstance(): AppDataBase{
            return if (mInstance == null){
                Room.databaseBuilder(MainApp.getInstance().getContext(),AppDataBase::class.java,"room_database")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }else{
                mInstance!!
            }
        }

        private val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL DEFAULT 18")
            }

        }
    }

    abstract fun getUerDao(): UserDao
}