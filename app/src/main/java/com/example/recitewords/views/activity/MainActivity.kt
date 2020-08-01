package com.example.recitewords.views.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recitewords.databinding.ActivityMainBinding
import com.example.recitewords.utlis.InjectorUtils
import com.example.recitewords.utlis.showFragment
import com.example.recitewords.viewmodel.ScreenSlidePageViewModel
import com.example.recitewords.views.frames.MainFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)
        showFragment<MainFragment>(binding.mainContent.id)


    }

}
