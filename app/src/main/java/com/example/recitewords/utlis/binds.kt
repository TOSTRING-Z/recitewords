package com.example.recitewords.utlis

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isGone")
fun View.isGone(isGone: Boolean) {
    this.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}