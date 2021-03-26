package com.istekno.mycalculator.ui.calculator

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.istekno.mycalculator.R
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class ClearEqualOperations(private val context: Context, private val tvCalculation: TextView, private val tvResult: TextView, private val listener: CalculatorListener){
    
    fun actionEqualsListener(view: View) {
        try {
            val expression = ExpressionBuilder(tvCalculation.text.toString()).build().evaluate()
            val longResult = expression.toLong()

            if (expression == longResult.toDouble()) {
                onEqual(view, "$longResult")
            } else {
                onEqual(view, expression.toString())
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun actionBackspace(view: View) {
        val expression = tvCalculation.text.toString()

         if (view.id == R.id.btn_clear) {
            tvCalculation.text = ""
            tvResult.text = ""
        } else if (expression.isNotEmpty()) {
            when {
                expression[expression.length - 1] == '.' && expression[expression.length - 2] == '0' -> tvCalculation.text = expression.removeSuffix("0.")
                expression.endsWith("sqrt(") -> tvCalculation.text = expression.removeSuffix("sqrt(")
                expression.endsWith("sin(") -> tvCalculation.text = expression.removeSuffix("sin(")
                expression.endsWith("cos(") -> tvCalculation.text = expression.removeSuffix("cos(")
                expression.endsWith("tan(") -> tvCalculation.text = expression.removeSuffix("tan(")
                expression.endsWith("asin(") -> tvCalculation.text = expression.removeSuffix("asin(")
                expression.endsWith("acos(") -> tvCalculation.text = expression.removeSuffix("acos(")
                expression.endsWith("atan(") -> tvCalculation.text = expression.removeSuffix("atan(")
                expression.endsWith("sinh(") -> tvCalculation.text = expression.removeSuffix("sinh(")
                expression.endsWith("cosh(") -> tvCalculation.text = expression.removeSuffix("cosh(")
                expression.endsWith("tanh(") -> tvCalculation.text = expression.removeSuffix("tanh(")
                expression.endsWith("log(") -> tvCalculation.text = expression.removeSuffix("log(")
                expression.endsWith("log10(") -> tvCalculation.text = expression.removeSuffix("log10(")
                else -> tvCalculation.text = expression.substring(0, expression.length - 1)
            }
        }
    }

    private fun onEqual(view: View, expression: String) {
        tvResult.text = expression
        tvResult.textSize = 40F
        tvResult.typeface = ResourcesCompat.getFont(view.context, R.font.poppins_bold)
        tvCalculation.textSize = 20F
        tvCalculation.typeface = ResourcesCompat.getFont(view.context, R.font.poppins_regular)

        listener.addHistory(tvCalculation.text.toString(), tvResult.text.toString())
    }
}