package com.example.recitewords.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recitewords.data.table.Words
import com.example.recitewords.databinding.FragmentCardBinding
import com.example.recitewords.viewmodel.ScreenSlidePageViewModel
import com.example.recitewords.views.activity.ScreenSlidePagerActivity
import java.io.IOException


class CardPagerAdapter(
    val context: Context
) : ListAdapter<Words, CardPagerViewHolder>(CardPagerDiffUtil()) {
    override fun submitList(list: MutableList<Words>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardPagerViewHolder {
        val view = FragmentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardPagerViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: CardPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: CardPagerViewHolder) {
        super.onViewRecycled(holder)
    }
}

class CardPagerViewHolder(
    val binding: FragmentCardBinding,
    val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.word.setOnClickListener { binding.explain.visibility = View.VISIBLE }
    }

    fun bind(words: Words?) {
        words?.let { w ->

            binding.words = w
            binding.setKonwOnclick {
                if (binding.explain.visibility == View.GONE) {
                    binding.explain.visibility = View.VISIBLE
                } else {
                    w.state = if (w.state - 2 < 0) -1 else w.state - 2
                    ScreenSlidePageViewModel.getInstance().update(listOf(w.id), w.state)
                    binding.explain.visibility = View.GONE
                    if (w.state == -1)
                        (context as ScreenSlidePagerActivity).submit(w)
                    else
                        (context as ScreenSlidePagerActivity).shuffle()
                }
            }
            binding.setDkonwOnclick {
                if (binding.explain.visibility == View.GONE) {
                    binding.explain.visibility = View.VISIBLE
                } else {
                    w.state = 3
                    ScreenSlidePageViewModel.getInstance().update(listOf(w.id), w.state)
                    binding.explain.visibility = View.GONE
                    (context as ScreenSlidePagerActivity).shuffle()
                }
            }
            binding.setDimOnclick {
                if (binding.explain.visibility == View.GONE) {
                    binding.explain.visibility = View.VISIBLE
                } else {
                    w.state = 5
                    ScreenSlidePageViewModel.getInstance().update(listOf(w.id), w.state)
                    binding.explain.visibility = View.GONE
                    (context as ScreenSlidePagerActivity).shuffle()
                }
            }
            binding.setAudioOnclick {
                try {
                    val mediaPlayer = MediaPlayer()
                    mediaPlayer.setDataSource("https://fanyi.baidu.com/gettts?lan=en&text=${w.word}&spd=3&source=web")
                    mediaPlayer.prepare()
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start();
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            binding.explain.visibility = View.GONE
        }
    }
}

class CardPagerDiffUtil : DiffUtil.ItemCallback<Words>() {
    override fun areItemsTheSame(oldItem: Words, newItem: Words): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Words, newItem: Words): Boolean {
        return oldItem.equals(newItem)
    }
}
