package com.example.recitewords.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recitewords.viewmodel.ScreenSlidePageViewModel
import com.example.recitewords.repository.WordsRepository

class ScreenSlidePageFactory (
    private val repository: WordsRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ScreenSlidePageViewModel(
                repository
            ) as T
        }
}