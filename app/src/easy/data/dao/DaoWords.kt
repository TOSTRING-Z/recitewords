package com.example.easy.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.easy.data.table.Words

@Dao
interface DaoWords {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(Words: List<Words>)

    @Query("SELECT * FROM Words WHERE id<:id LIMIT 50")
    fun getWords(id: Int): LiveData<List<Words>>

    @Update
    suspend fun update(Words: Words)
}