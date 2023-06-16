package com.trajectory27.jetpackdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trajectory27.jetpackdemo.bean.User
import com.trajectory27.jetpackdemo.dao.UserDao

/**
 * @author Trajectory27
 * @description Room数据库
 * @date 2023/6/16 10:07
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        // Singleton
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build().apply { instance = this }
        }
    }
}