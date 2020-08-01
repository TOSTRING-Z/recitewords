package com.example.recitewords.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recitewords.data.table.User

@Dao
interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(User: User)

    @Query("SELECT COUNT(*) FROM user")
    fun count(): Int

    @Query("SELECT day FROM user LIMIT 1")
    fun getDay(): String

    @Query("SELECT DayWordsNum FROM user LIMIT 1")
    fun getDayWordsNum(): LiveData<Int>

    @Query("UPDATE User SET day=:day")
    suspend fun setDay(day: String): Int

    @Query("UPDATE User SET dayWordsNum=:dayWordsNum")
    suspend fun setDayWordsNum(dayWordsNum: Int)
}