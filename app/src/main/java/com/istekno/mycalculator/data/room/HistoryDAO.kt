package com.istekno.mycalculator.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM history_table ORDER BY created_at DESC")
    fun getHistory(): Flow<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history: HistoryEntity)

    @Query("DELETE FROM history_table")
    suspend fun deleteAllHistory()
}