package com.maurorodriguez1994.didi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maurorodriguez1994.didi.room.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table")
    fun getWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("SELECT COUNT(id) FROM word_table")
    fun getCount(): Flow<Int>

   /* @Query("DELETE FROM word_table")
    suspend fun deleteAll()*/
}