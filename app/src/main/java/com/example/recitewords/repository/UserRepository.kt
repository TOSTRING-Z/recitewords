package com.example.recitewords.repository

import androidx.lifecycle.LiveData
import com.example.recitewords.data.AppDataBase
import com.example.recitewords.data.dao.DaoUser
import com.example.recitewords.data.table.User

class UserRepository(
    private val daoUser: DaoUser
) {
    val mDayWordsNum: LiveData<Int> = daoUser.getDayWordsNum()
    fun count() = daoUser.count()
    fun getDay() = daoUser.getDay()
    fun getDayWordsNum() = daoUser.getDayWordsNum()
    suspend fun setDay(day: String) {
        daoUser.setDay(day)
    }
    suspend fun setDayWordsNum(dayWordsNum: Int) {
        daoUser.setDayWordsNum(dayWordsNum)
    }
    suspend fun insert(user: User) {
        daoUser.insert(user)
    }

    companion object {
        private var INSTANCE: UserRepository? = null

        fun getInstance(): UserRepository {
            INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = UserRepository(AppDataBase.getInstance().DaoUser())
                }
            }
            return INSTANCE!!
        }
    }

}

