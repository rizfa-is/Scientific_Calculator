package com.istekno.mycalculator.ui.calculator

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.istekno.mycalculator.R

class MathematicalOperations(private val view: View, private val tvCalculation: TextView, private val tvResult: TextView) {

    private val listNumber = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val listOperator = mutableListOf(".", "(", ")", "/", "*", "-", "+", "%", "^")
    private val scientificOp = arrayOf("sin","cos","tan","asin","acos","atan","sinh","cosh","tanh","log","log10")
    private var str = (view as Button).text.toString()

    fun actionGeneral() {
        for (i in scientificOp.indices) {
            if (scientificOp.contains(str)) str += "("
        }

        if (str == "√") {
            str = "sqrt("
        } else if (str == "x^") {
            str = "^"
        }

        if (tvResult.text.isEmpty()) {
            appendCalc(str, false)
        } else {
            appendCalc( str, true)
        }
    }

    fun actionDot() {
        val operation = tvCalculation.text
        listOperator.remove(".")

        for (i in listOperator.indices) {
            if (operation.endsWith(listOperator[i])) onCalculation("0.")
        }

        when {
            operation.endsWith('.') -> onCalculation("")
            operation.length > 1 -> {
                for (i in listOperator.indices) {
                    val list = operation.split(".")

                    if (list.last().contains(listOperator[i])) onCalculation(".")
                }
            }
            operation.isNotEmpty() -> onCalculation(".")
            operation.isEmpty() -> onCalculation("0.")
        }
    }

    private fun appendCalc(string: String, isClear: Boolean) {
        if (isClear) {
            if (tvResult.text.isNotEmpty() && !listNumber.contains(string)) {
                onClearCalc(tvResult.text.toString(), string)
            } else {
                onClearCalc("", string)
            }
        } else {
            val listCalculate = tvCalculation.text.toList()

            if (listCalculate.isNotEmpty()) {
                val str = listCalculate[listCalculate.size - 1].toString()

                if (!listOperator.contains(string)) {
                    onCalculation(string)
                } else {
                    if (str != string && (!listOperator.contains(str) || listNumber.contains(string))) onCalculation(string)
                }

            } else {
                if (listOperator.contains(string)) onCalculation("") else onCalculation(string)
            }
        }
    }

    private fun onCalculation(number: String) {
        tvCalculation.append(number)
        tvCalculation.textSize = 40F
        tvCalculation.typeface = ResourcesCompat.getFont(view.context, R.font.poppins_bold)
        tvResult.textSize = 20F
        tvResult.typeface = ResourcesCompat.getFont(view.context, R.font.poppins_regular)
    }

    private fun onClearCalc(operation: String, string: String) {
        tvCalculation.text = operation
        tvResult.text = ""
        onCalculation(string)
    }
}