package com.example.recitewords.data

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recitewords.AppEasy
import com.example.recitewords.data.dao.DaoUser
import com.example.recitewords.data.dao.DaoWords
import com.example.recitewords.data.table.User
import com.example.recitewords.data.table.Words

@Database(
    entities = [Words::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun DaoWords(): DaoWords
    abstract fun DaoUser(): DaoUser

    companion object {
        private var INSTANCE: AppDataBase? = null
        private const val DB_NAME = "EASY_DB"

        @JvmStatic
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