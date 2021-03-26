package com.istekno.mycalculator.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.istekno.mycalculator.data.HistoryRepository
import com.istekno.mycalculator.data.room.HistoryEntity
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository): ViewModel() {

    val allHistory: LiveData<List<HistoryEntity>> = repository.allHistory.asLiveData()

    fun insertHistory(history: HistoryEntity) = viewModelScope.launch {
        repository.insert(history)
    }

    fun deleteAllHistory() = viewModelScope.launch {
        repository.delete()
    }
}