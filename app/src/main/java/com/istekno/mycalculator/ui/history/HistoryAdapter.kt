package com.istekno.mycalculator.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.istekno.mycalculator.data.room.HistoryEntity
import com.istekno.mycalculator.databinding.ItemsHistoryBinding
import java.util.*

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val listHistory = mutableListOf<HistoryEntity>()

    fun insertList(history: List<HistoryEntity>) {
        listHistory.clear()
        listHistory.addAll(history)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ItemsHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryEntity) {
            binding.tvOperation.text = history.operation
            binding.tvResult.text = history.result
            binding.tvCreatedAt.text = formatDate(history.createdAt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemsHistoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    override fun getItemCount(): Int = listHistory.size

    private fun formatDate(date: Long) : String {
        val formattedDate: String
        val c = Calendar.getInstance()
        c.timeInMillis = date
        formattedDate = "${c.get(Calendar.DAY_OF_MONTH)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.YEAR)} ${c.get(
            Calendar.HOUR_OF_DAY)}:${c.get(Calendar.MINUTE)}"

        return formattedDate
    }
}