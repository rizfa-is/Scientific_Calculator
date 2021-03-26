package com.istekno.mycalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.istekno.mycalculator.data.HistoryRepository
import com.istekno.mycalculator.ui.history.HistoryViewModel

@Suppress("UNCHECKED_CAST")
class HistoryViewModelFactory(private val repository: HistoryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}