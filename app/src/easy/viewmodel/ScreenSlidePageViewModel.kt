package com.example.easy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easy.data.AppDataBase
import com.example.easy.repository.WordsRepository
import com.example.easy.utlis.InjectorUtils.getWordsRepository

class ScreenSlidePageViewModel(private val repository: WordsRepository) : ViewModel() {

    val words by lazy(LazyThreadSafetyMode.NONE) {
        repository.getWords(0)
    }

    companion object {
        private var INSTANCE: ScreenSlidePageViewModel? = null

        fun getInstance(): ScreenSlidePageViewModel {
            INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE =
                            ScreenSlidePageViewModel(
                                    getWordsRepository(AppDataBase.getInstance().DaoWords())
                            )
                }
            }
            return INSTANCE!!
        }
    }
}