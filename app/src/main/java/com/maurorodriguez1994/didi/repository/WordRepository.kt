package com.maurorodriguez1994.didi.repository

import androidx.annotation.WorkerThread
import com.maurorodriguez1994.didi.room.dao.WordDao
import com.maurorodriguez1994.didi.room.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}