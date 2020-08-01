package com.example.easy.views.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easy.databinding.ActivityMainBinding
import com.example.easy.utlis.showFragment
import com.example.easy.views.frames.MainFragment


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
