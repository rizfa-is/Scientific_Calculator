package com.istekno.mycalculator.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.istekno.mycalculator.databinding.FragmentHistoryBinding
import com.istekno.mycalculator.di.HistoryInjection
import com.istekno.mycalculator.ui.Dialog
import com.istekno.mycalculator.viewmodel.HistoryViewModelFactory

class HistoryFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((activity?.application as HistoryInjection).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = HistoryAdapter()
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = historyAdapter
        }

        populateList(historyAdapter)

        val dialog = Dialog(view.context)
        binding.buttonClearHistory.setOnClickListener {
            dialog.dialog("Are you sure to delete all history ?") {
                viewModel.deleteAllHistory()
            }
        }
    }

    private fun populateList(adapter: HistoryAdapter) {
        viewModel.allHistory.observe(viewLifecycleOwner) { data ->
            if (data.isNullOrEmpty()) {
                binding.tvNoHistory.visibility = View.VISIBLE
                binding.buttonClearHistory.visibility = View.GONE
            } else {
                binding.tvNoHistory.visibility = View.GONE
                binding.buttonClearHistory.visibility = View.VISIBLE
            }

            data.let { adapter.insertList(it) }
        }
    }
}