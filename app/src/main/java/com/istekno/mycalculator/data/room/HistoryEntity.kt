package com.istekno.mycalculator.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @ColumnInfo(name = "operation") val operation: String,
    @ColumnInfo(name = "result") val result: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
