package com.lzk.jetpacktest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/7
 * Function:用户表
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "user_name")
    val username: String,
    val age: Int
)