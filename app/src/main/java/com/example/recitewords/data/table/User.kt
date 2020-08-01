package com.example.recitewords.data.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val dayWordsNum: Int,
    val day: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}