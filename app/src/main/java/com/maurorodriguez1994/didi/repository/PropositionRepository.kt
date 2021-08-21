package com.maurorodriguez1994.didi.repository

import androidx.annotation.WorkerThread
import com.maurorodriguez1994.didi.room.dao.PropositionDao
import com.maurorodriguez1994.didi.room.entity.Proposition
import kotlinx.coroutines.flow.Flow

class PropositionRepository(private val propositionDao: PropositionDao) {
    val propositions: Flow<List<Proposition>> = propositionDao.getPropositions()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(proposition: Proposition) {
        propositionDao.insert(proposition)
    }
}