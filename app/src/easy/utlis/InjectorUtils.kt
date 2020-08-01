package com.example.easy.utlis

import com.example.easy.data.dao.DaoWords
import com.example.easy.repository.WordsRepository

object InjectorUtils {
    fun getWordsRepository(daoWords: DaoWords) =
        WordsRepository(daoWords)
}