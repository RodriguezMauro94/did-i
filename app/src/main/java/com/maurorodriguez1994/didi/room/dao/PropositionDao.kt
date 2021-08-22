package com.maurorodriguez1994.didi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maurorodriguez1994.didi.room.entity.Proposition
import kotlinx.coroutines.flow.Flow

@Dao
interface PropositionDao {
    @Query("SELECT * FROM proposition_table ORDER BY state")
    fun getPropositions(): Flow<List<Proposition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proposition: Proposition)
}