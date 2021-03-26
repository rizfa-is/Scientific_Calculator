package com.istekno.mycalculator.di

import android.app.Application
import com.istekno.mycalculator.data.HistoryRepository
import com.istekno.mycalculator.data.room.HistoryRoomDatabase

class HistoryInjection: Application() {

    val database by lazy { HistoryRoomDatabase.getDatabase(this) }
    val repository by lazy { HistoryRepository(database.historyDao()) }
}