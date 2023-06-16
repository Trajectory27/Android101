package com.trajectory27.jetpackdemo.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.*
import com.trajectory27.jetpackdemo.bean.User
import com.trajectory27.jetpackdemo.database.AppDatabase
import com.trajectory27.jetpackdemo.databinding.ActivityMainBinding
import com.trajectory27.jetpackdemo.viewmodel.MainViewModel
import com.trajectory27.jetpackdemo.viewmodel.MainViewModelFactory
import kotlin.concurrent.thread

/**
 * @author Trajectory27
 * @description 主页面
 * @date 2023/6/15 9:53
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sp = getPreferences(Context.MODE_PRIVATE)
        val counterReserved = sp.getInt("count_reserved", 0);

        // 从工厂创建ViewModel
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(counterReserved)
        ).get(MainViewModel::class.java)

        binding.btnPlus.setOnClickListener {
            viewModel.plusOne()
        }
        refreshCounter()

        binding.btnClear.setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this) { counter ->
            binding.tvInfo.text = counter.toString()
        }

        binding.btnGetUser.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this) { user ->
            binding.tvInfo.text = user.firstName
        }

        // 数据库
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)

        binding.btnAddData.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.btnUpdateData.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        binding.btnDeleteData.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        binding.btnQueryData.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }
    }

    private fun refreshCounter() {
        binding.tvInfo.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }

}


/*
package com.trajectory27.jetpackdemo.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.trajectory27.jetpackdemo.databinding.ActivityMainBinding
import com.trajectory27.jetpackdemo.viewmodel.MainViewModel
import com.trajectory27.jetpackdemo.viewmodel.MainViewModelFactory

*/
/**
 * @author Trajectory27
 * @description 主页面旧写法
 * @date 2023/6/15 9:53
 *//*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sp = getPreferences(Context.MODE_PRIVATE)

*/
/*        // 初始化ViewModel的方法
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)*//*


        val counterReserved = sp.getInt("count_reserved", 0);
        // 从工厂创建ViewModel
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(counterReserved)
        ).get(MainViewModel::class.java)

        binding.btnPlus.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        refreshCounter()

        binding.btnClear.setOnClickListener {
            viewModel.counter = 0;
            refreshCounter()
        }
    }

    private fun refreshCounter() {
        binding.tvInfo.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }
}*/
