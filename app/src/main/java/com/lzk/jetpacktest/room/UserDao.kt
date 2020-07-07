package com.lzk.jetpacktest.room

import androidx.room.*

/**
 * Author: LiaoZhongKai.
 * Date: 2020/7/7
 * Function:用户表操作类
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Insert
    fun addUser(vararg user: User)

    @Query("DELETE FROM user")
    fun deleteAllUser()

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}