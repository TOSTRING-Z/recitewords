package com.example.easy.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easy.viewmodel.ScreenSlidePageViewModel
import com.example.easy.repository.WordsRepository

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