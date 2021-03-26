package com.istekno.mycalculator.data

import androidx.annotation.WorkerThread
import com.istekno.mycalculator.data.room.HistoryDAO
import com.istekno.mycalculator.data.room.HistoryEntity
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDAO: HistoryDAO) {

    val allHistory: Flow<List<HistoryEntity>> = historyDAO.getHistory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(history: HistoryEntity) {
        historyDAO.addHistory(history)
    }

    @WorkerThread
    suspend fun delete() {
        historyDAO.deleteAllHistory()
    }
}