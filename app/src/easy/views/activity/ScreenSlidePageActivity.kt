package com.example.easy.views.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.easy.adapter.CardPagerAdapter
import com.example.easy.databinding.ActivityScreenSlidePagerBinding
import com.example.easy.viewmodel.ScreenSlidePageViewModel

class ScreenSlidePagerActivity : FragmentActivity() {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var binding: ActivityScreenSlidePagerBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenSlidePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = binding.pager

        // The pager adapter, which provides the pages to the view pager widget.
        viewPager.adapter = ScreenSlidePageViewModel.getInstance().words.value?.let { CardPagerAdapter(it) }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            finish()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}

