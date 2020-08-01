package com.example.recitewords

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.recitewords.data.table.User
import com.example.recitewords.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("Registered")
class AppEasy : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext

        //初始化User
        if (UserViewModel.getInstance().count() == 0)
            UserViewModel.getInstance().insert(User(50, SimpleDateFormat("yyyy-MM-dd").format(Date())))

    }

    companion object {
        private var appContext: Context? = null
        fun getContext() = appContext!!
    }

}
