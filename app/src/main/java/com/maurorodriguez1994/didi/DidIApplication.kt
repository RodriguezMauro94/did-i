package com.maurorodriguez1994.didi

import android.app.Application
import com.maurorodriguez1994.didi.repository.PropositionRepository
import com.maurorodriguez1994.didi.repository.WordRepository
import com.maurorodriguez1994.didi.room.database.DidIDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DidIApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DidIDatabase.getDatabase(this, applicationScope) }
    val wordRepository by lazy { WordRepository(database.wordDao()) }
    val propositionRepository by lazy { PropositionRepository(database.propositionDao()) }
}