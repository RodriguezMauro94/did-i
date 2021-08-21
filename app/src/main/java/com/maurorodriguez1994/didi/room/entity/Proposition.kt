package com.maurorodriguez1994.didi.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "proposition_table", primaryKeys = ["question_id", "word_id"])
data class Proposition(
    @ColumnInfo(name = "question_id")
    val questionId: String,
    @ColumnInfo(name = "word_id")
    val wordId: String,
    val state: Boolean
)