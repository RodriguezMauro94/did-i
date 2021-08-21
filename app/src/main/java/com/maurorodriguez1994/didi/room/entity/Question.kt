package com.maurorodriguez1994.didi.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(@PrimaryKey @ColumnInfo(name = "question_id") val question: String)