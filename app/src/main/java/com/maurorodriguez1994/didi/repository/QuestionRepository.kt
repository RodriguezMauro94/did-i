package com.maurorodriguez1994.didi.repository

import androidx.annotation.WorkerThread
import com.maurorodriguez1994.didi.room.dao.QuestionDao
import com.maurorodriguez1994.didi.room.entity.Question
import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {
    val questions: Flow<List<Question>> = questionDao.getQuestions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(question: Question) {
        questionDao.insert(question)
    }
}