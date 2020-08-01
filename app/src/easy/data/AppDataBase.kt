package com.example.easy.data

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easy.AppEasy
import com.example.easy.data.dao.DaoWords
import com.example.easy.data.table.Words

@Database(
    entities = [Words::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun DaoWords(): DaoWords

    companion object {
        private var INSTANCE: AppDataBase? = null
        private const val DB_NAME = "EASY_DB"

        fun getInstance(): AppDataBase {
            INSTANCE ?: synchronized(AppDataBase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        AppEasy.getContext(),
                        AppDataBase::class.java,
                        DB_NAME
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}