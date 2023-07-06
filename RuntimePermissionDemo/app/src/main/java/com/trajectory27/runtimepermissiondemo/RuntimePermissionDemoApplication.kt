package com.trajectory27.runtimepermissiondemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hjq.toast.Toaster

/**
 * @author Trajectory27
 * @description 基础Application
 * @date 2023/6/27 21:16
 */
class RuntimePermissionDemoApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Toaster.init(this)
    }
}