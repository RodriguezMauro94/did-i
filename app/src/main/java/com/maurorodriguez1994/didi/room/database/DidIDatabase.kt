package com.maurorodriguez1994.didi.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.maurorodriguez1994.didi.room.dao.QuestionDao
import com.maurorodriguez1994.didi.room.dao.WordDao
import com.maurorodriguez1994.didi.room.entity.Question
import com.maurorodriguez1994.didi.room.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class DidIDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun questionDao(): QuestionDao

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
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // FIXME corregir con PK
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
            var question = Question(1, "Close")
            questionDao.insert(question)

            question = Question(2, "Open")
            questionDao.insert(question)

            question = Question(3, "Lock")
            questionDao.insert(question)

            question = Question(4, "Get inside")
            questionDao.insert(question)
        }
    }
}