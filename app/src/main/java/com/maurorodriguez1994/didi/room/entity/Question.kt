package com.maurorodriguez1994.didi.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")val id: Int, val description: String)