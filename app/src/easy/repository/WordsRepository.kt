package com.example.easy.repository

import com.example.easy.data.dao.DaoWords
import com.example.easy.data.table.Words
import com.example.easy.data.AppDataBase

class WordsRepository(
    private val daoWords: DaoWords
) {
    fun getWords(id: Int) = daoWords.getWords(id)
    suspend fun insert(words: List<Words>) {
        daoWords.insertAll(words)
    }
    suspend fun update(words: Words) {
        daoWords.update(words)
    }

    companion object {
        private var INSTANCE: WordsRepository? = null

        fun getInstance(): WordsRepository {
            INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = WordsRepository(AppDataBase.getInstance().DaoWords())
                }
            }
            return INSTANCE!!
        }
    }

}