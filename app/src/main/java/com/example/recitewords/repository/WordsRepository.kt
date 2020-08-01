package com.example.recitewords.repository

import androidx.lifecycle.LiveData
import com.example.recitewords.data.dao.DaoWords
import com.example.recitewords.data.table.Words
import com.example.recitewords.data.AppDataBase
class WordsRepository(
    private val daoWords: DaoWords
) {
    val mCount: LiveData<Int> = daoWords.count()
    val mConutRemenber: LiveData<Int> = daoWords.conutRemenber()
    fun get(limit: Int):MutableList<Words> = daoWords.get(limit)

    suspend fun insert(words: List<Words>) {
        daoWords.insertAll(words)
    }

    suspend fun update(ids: List<Int>, state: Int) {
        daoWords.update(ids,state)
    }

    suspend fun delete() {
        daoWords.delete()
    }

    fun getCur(limit: Int): MutableList<Words> {
        return daoWords.getCur(limit)
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

