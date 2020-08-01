package com.example.recitewords.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recitewords.data.table.Words

@Dao
interface DaoWords {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(Words: List<Words>)

    @Query("SELECT COUNT(*) FROM Words WHERE state==-1")
    fun conutRemenber(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM Words")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM Words WHERE state>=0 ORDER BY state DESC,rank DESC LIMIT :limit")
    fun get(limit: Int): MutableList<Words>

    @Query("SELECT * FROM Words WHERE state>=1 ORDER BY state DESC,rank DESC LIMIT :limit")
    fun getCur(limit: Int): MutableList<Words>

    @Query("UPDATE Words SET state=:state WHERE id in (:ids)")
    suspend fun update(ids: List<Int>,state: Int): Int

    @Query("DELETE FROM Words")
    suspend fun delete()

}