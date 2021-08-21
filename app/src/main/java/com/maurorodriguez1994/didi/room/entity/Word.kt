package com.maurorodriguez1994.didi.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int, val word: String)