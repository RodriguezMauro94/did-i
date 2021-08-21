package com.maurorodriguez1994.didi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maurorodriguez1994.didi.room.entity.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question_table")
    fun getQuestions(): Flow<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)
}