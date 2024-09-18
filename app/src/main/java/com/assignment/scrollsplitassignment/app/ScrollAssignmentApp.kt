package com.assignment.scrollsplitassignment.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class ScrollAssignmentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        deleteCache(this)
    }

    companion object {
        lateinit var mInstance: ScrollAssignmentApp
        fun getContext(): Context? {
            return mInstance.applicationContext
        }

        @get:Synchronized
        var instance: ScrollAssignmentApp? = null
            private set
    }

    private fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children: Array<String> = dir.list()
            for (child in children) {
                val success = deleteDir(File(dir, child))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }
}