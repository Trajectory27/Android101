package com.trajectory27.jetpackdemo.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Trajectory27
 * @description
 * @date 2023/6/15 16:33
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}