package com.maurorodriguez1994.didi

import android.app.Application
import com.maurorodriguez1994.didi.repository.WordRepository
import com.maurorodriguez1994.didi.room.database.DidIDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DidIDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}