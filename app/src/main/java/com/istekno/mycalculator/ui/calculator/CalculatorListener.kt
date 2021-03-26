package com.istekno.mycalculator.ui.calculator

interface CalculatorListener {

    fun addHistory(operation: String, result: String)
}