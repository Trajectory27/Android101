package com.trajectory27.runtimepermissiondemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.XXPermissions
import com.trajectory27.runtimepermissiondemo.databinding.ActivityMainBinding
import java.lang.Exception


/**
 * 拨打电话权限常量
 */
const val CALL_PHONE_PERMISSION = 1

/**
 * 获取很多权限常量
 */
const val A_LOT_PERMISSIONS = 2

/**
 * @author Trajectory27
 * @description 权限管理在Kotlin中的写法
 * @date 2023/6/27 15:52
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * 一次请求一个权限 检查权限 -> 请求权限
         */
        binding.btnMakeCall.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PHONE_PERMISSION
                )
            } else {
                makeCall()
            }
        }

        /**
         * 一次请求多个权限
         */
        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
        )
        val unPermissions = ArrayList<String>()

        binding.btnRequestMultiPer.setOnClickListener {
            unPermissions.clear()
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    unPermissions.add(permission)
                }
            }
            if (unPermissions.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    this,
                    unPermissions.toTypedArray(),
                    A_LOT_PERMISSIONS
                )
            }
        }

        /**
         * 使用XXPermission库
         */
        binding.btnUseXX.setOnClickListener {
            XXPermissions.with(this)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .permission(Manifest.permission.CALL_PHONE)
                .permission(Manifest.permission.CAMERA)
                .request(object : OnPermissionCallback {
                    override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                        if (!allGranted) {
                            // 有且仅有部分权限授予成功
                            Toast.makeText(this@MainActivity, "部分权限授予成功", Toast.LENGTH_SHORT).show()
                        } else {
                            // 全部权限授予成功
                            Toast.makeText(this@MainActivity, "全部权限授予成功", Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onDenied(
                        permissions: MutableList<String>,
                        doNotAskAgain: Boolean
                    ) {
                        super.onDenied(permissions, doNotAskAgain)
                        if (doNotAskAgain) {
                            // 永久拒绝任意一个权限
                            Toast.makeText(this@MainActivity, "永久拒绝权限，需要手动开启", Toast.LENGTH_SHORT).show()
                            XXPermissions.startPermissionActivity(this@MainActivity, permissions)
                        } else {
                            // 有权限获取失败，但是所有权限都可以再次直接请求
                            Toast.makeText(this@MainActivity, "获取权限失败", Toast.LENGTH_SHORT).show()
                        }

                    }
                })
        }
    }

    /**
     * 请求权限的回调函数
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            CALL_PHONE_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall()
            } else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
            }

            A_LOT_PERMISSIONS -> if (grantResults.isNotEmpty()) {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
            }
        }
    }


    /**
     * 拨打电话
     */
    private fun makeCall() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}