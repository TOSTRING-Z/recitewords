package com.example.recitewords.views.activity

import android.R
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recitewords.adapter.CardPagerAdapter
import com.example.recitewords.data.table.Words
import com.example.recitewords.databinding.ActivityScreenSlidePagerBinding
import com.example.recitewords.viewmodel.ScreenSlidePageViewModel
import com.example.recitewords.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*


class ScreenSlidePagerActivity : FragmentActivity() {

    private lateinit var binding: ActivityScreenSlidePagerBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: CardPagerAdapter
    private var words: MutableList<Words> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenSlidePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.pager

        adapter = CardPagerAdapter(this)
        viewPager.adapter = adapter

        val timeCurrency = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val time = UserViewModel.getInstance().getDay()
        words.clear()
        val dayWordsNum = intent.getIntExtra("dayWordsNum",50)
        if (time != timeCurrency) {
            UserViewModel.getInstance().setDay(timeCurrency)
            words.addAll(ScreenSlidePageViewModel.getInstance().get(dayWordsNum, true))
        } else {
            words.addAll(ScreenSlidePageViewModel.getInstance().get(dayWordsNum))
            if (words.size == 0)
                words.addAll(ScreenSlidePageViewModel.getInstance().get(dayWordsNum, true))
        }
        adapter.submitList(words)
    }

    fun submit(w: Words) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            words.removeAll { it.id.equals(w.id) }
        }
        if (words.size == 0) finish()
        adapter.submitList(words)
    }

    fun shuffle() {
        if(words.size != 1)
            viewPager.currentItem = ((0 until words.size) subtract listOf(viewPager.currentItem)).random()
    }

    override fun onBackPressed() {
        finish()
    }

}

