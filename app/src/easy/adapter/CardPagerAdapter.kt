package com.example.easy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.easy.data.table.Words
import com.example.easy.databinding.FragmentCardBinding


class CardPagerAdapter(val words: List<Words>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FragmentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardPagerViewHolder(view)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as CardPagerViewHolder).bind(words.get(position))
    }

}

class CardPagerViewHolder(
    val bind: FragmentCardBinding
) : RecyclerView.ViewHolder(bind.root) {

    init {
    }

    fun bind(words: Words?) {
        words?.let { bind.words = it }
    }
}