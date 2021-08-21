package com.maurorodriguez1994.didi.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.maurorodriguez1994.didi.room.dao.PropositionDao
import com.maurorodriguez1994.didi.room.dao.QuestionDao
import com.maurorodriguez1994.didi.room.dao.WordDao
import com.maurorodriguez1994.didi.room.entity.Question
import com.maurorodriguez1994.didi.room.entity.Proposition
import com.maurorodriguez1994.didi.room.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class, Question::class, Proposition::class], version = 1, exportSchema = false)
abstract class DidIDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun questionDao(): QuestionDao
    abstract fun propositionDao(): PropositionDao

    companion object {
        @Volatile
        private var INSTANCE: DidIDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DidIDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DidIDatabase::class.java,
                    "did_i_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(DidIdDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class DidIdDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                        populateDatabase(database.questionDao())
                        populateDatabase(database.propositionDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            var word = Word("Door")
            wordDao.insert(word)

            word = Word("Window")
            wordDao.insert(word)

            word = Word("Gas")
            wordDao.insert(word)

            word = Word("Clothes")
            wordDao.insert(word)
        }

        suspend fun populateDatabase(questionDao: QuestionDao) {
            var question = Question("Close")
            questionDao.insert(question)

            question = Question("Open")
            questionDao.insert(question)

            question = Question("Lock")
            questionDao.insert(question)

            question = Question("Get inside")
            questionDao.insert(question)
        }

        suspend fun populateDatabase(propositionDao: PropositionDao) {
            var proposition = Proposition("Lock", "Door", false)
            propositionDao.insert(proposition)

            proposition = Proposition("Close", "Window", false)
            propositionDao.insert(proposition)

            proposition = Proposition("Close", "Gas", false)
            propositionDao.insert(proposition)

            proposition = Proposition("Get inside", "Clothes", false)
            propositionDao.insert(proposition)
        }
    }
}