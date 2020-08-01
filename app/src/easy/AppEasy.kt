package com.example.easy

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
class AppEasy : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
    }

    companion object {
        private var appContext: Context? = null
        fun getContext() = appContext!!
    }
}
