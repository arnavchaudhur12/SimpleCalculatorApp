package com.example.nehacalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var operand1 = ""
    private var operand2 = ""
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Set click listeners for number buttons
        findViewById<Button>(R.id.button0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.button1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.button2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.button3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.button4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.button5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.button6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.button7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.button8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.button9).setOnClickListener { appendNumber("9") }

        // Set click listeners for operation buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { setOperation("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { setOperation("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { setOperation("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperation("/") }

        // Set click listener for equals button
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }

        // Set click listener for clear button
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clear() }
    }

    private fun appendNumber(number: String) {
        if (operation.isEmpty()) {
            operand1 += number
            resultTextView.text = operand1
        } else {
            operand2 += number
            resultTextView.text = operand2
        }
    }

    private fun setOperation(op: String) {
        if (operand1.isNotEmpty() && operand2.isEmpty()) {
            operation = op
            resultTextView.text = "$operand1 $operation"
        }
    }

    private fun calculateResult() {
        if (operand1.isNotEmpty() && operand2.isNotEmpty() && operation.isNotEmpty()) {
            val num1 = operand1.toDouble()
            val num2 = operand2.toDouble()
            var result = 0.0
            when (operation) {
                "+" -> result = num1 + num2
                "-" -> result = num1 - num2
                "*" -> result = num1 * num2
                "/" -> {
                    if (num2 != 0.0) {
                        result = num1 / num2
                    } else {
                        resultTextView.text = "Error"
                    }
                }
            }
            resultTextView.text = result.toString()
            operand1 = result.toString()
            operand2 = ""
            operation = ""
        }
    }

    private fun clear() {
        operand1 = ""
        operand2 = ""
        operation = ""
        resultTextView.text = ""
    }
}
