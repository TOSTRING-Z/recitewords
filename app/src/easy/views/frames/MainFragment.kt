package com.example.easy.views.frames

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easy.databinding.FrameMainBinding
import com.example.easy.views.activity.ScreenSlidePagerActivity

class MainFragment : Fragment() {

    private lateinit var binding: FrameMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrameMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.remenber.setOnClickListener {
            val intent = Intent(activity, ScreenSlidePagerActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}
