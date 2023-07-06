package com.trajectory27.jetpackdemo.dao

import androidx.room.*
import com.trajectory27.jetpackdemo.bean.User

/**
 * @author Trajectory27
 * @description 操作数据库的Dao层，主要与这一层进行交互
 * @date 2023/6/16 10:00
 */
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int
}