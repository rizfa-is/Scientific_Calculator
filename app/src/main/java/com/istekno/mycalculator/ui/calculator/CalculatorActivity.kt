package com.istekno.mycalculator.ui.calculator

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.istekno.mycalculator.R
import com.istekno.mycalculator.data.room.HistoryEntity
import com.istekno.mycalculator.databinding.ActivityCalculatorBinding
import com.istekno.mycalculator.di.HistoryInjection
import com.istekno.mycalculator.ui.history.HistoryFragment
import com.istekno.mycalculator.ui.history.HistoryViewModel
import com.istekno.mycalculator.viewmodel.HistoryViewModelFactory

class CalculatorActivity: AppCompatActivity(), CalculatorListener {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var clearEqualOperations: ClearEqualOperations
    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((application as HistoryInjection).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clearEqualOperations = ClearEqualOperations(this, binding.tvCalculation, binding.tvResult, this)

        binding.btnHistory.setOnClickListener {
            HistoryFragment().show(supportFragmentManager, "dialog")
        }
    }

    fun onMathOp(view: View) {
        val mathematicsOperations = MathematicalOperations(view, binding.tvCalculation, binding.tvResult)
        mathematicsOperations.actionGeneral()
    }

    fun onCLear(view: View) {
        clearEqualOperations.actionBackspace(view)
    }

    fun onEqual(view: View) {
        clearEqualOperations.actionEqualsListener(view)
    }

    override fun addHistory(operation: String, result: String) {
        viewModel.insertHistory(HistoryEntity(operation, result, System.currentTimeMillis()))
    }

    fun onDot(view: View) {
        val mathematicsOperations = MathematicalOperations(view, binding.tvCalculation, binding.tvResult)
        mathematicsOperations.actionDot()
    }

    fun onSecond(view: View) {
        val second = binding.btnSin.text.toString()

        if (second == "sin") {
            binding.btnSin.text = getString(R.string.asin)
            binding.btnCos.text = getString(R.string.acos)
            binding.btnTan.text = getString(R.string.atan)
        } else {
            binding.btnSin.text = getString(R.string.sin)
            binding.btnCos.text = getString(R.string.cos)
            binding.btnTan.text = getString(R.string.tan)
        }
    }
}