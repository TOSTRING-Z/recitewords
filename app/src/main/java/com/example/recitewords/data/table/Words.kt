package com.example.recitewords.data.table

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("word")])
data class Words(
    val word: String,
    val rank: Int,
    val explain: String,
    var state: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}