package com.example.calculatoropen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var lastNumeric: Boolean = false
    private var lastBot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControl()
    }

    private fun initControl() {
        buttonPlus.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonDecimal.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
        buttonZero.setOnClickListener(this)
        buttonOne.setOnClickListener(this)
        buttonTwo.setOnClickListener(this)
        buttonThree.setOnClickListener(this)
        buttonFour.setOnClickListener(this)
        buttonFive.setOnClickListener(this)
        buttonSix.setOnClickListener(this)
        buttonSeven.setOnClickListener(this)
        buttonEight.setOnClickListener(this)
        buttonNine.setOnClickListener(this)
    }

    private fun onAddNumber(view: View) {
        textInput.append((view as Button).text)
        lastNumeric = true
    }

    private fun onCheckDecimalPoint() {
        if (lastNumeric && !lastBot) {
            textInput.append(".")
            lastNumeric = false
            lastBot = true
        }
    }

    private fun onAddClear() {
        textInput.text = ""
        lastNumeric = false
        lastBot = false
    }


    private fun onAddEqual() {
        if (lastNumeric) {
            var tvValue = textInput.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("-") -> {
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        val two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textInput.text =
                            removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    tvValue.contains("+") -> {
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        val two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textInput.text =
                            removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    tvValue.contains("*") -> {
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        val two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textInput.text =
                            removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    tvValue.contains("/") -> {
                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        val two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        textInput.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                }
            } catch (e: ArithmeticException) {
                throw ArithmeticException("Have $e")
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String =
        if (result.endsWith(".0")) {
            result.substring(0, result.length - 2)
        } else {
            result
        }

    private fun getOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(textInput.text.toString())) {
            textInput.append((view as Button).text)
            lastNumeric = false
            lastBot = false
        }

        if (!textInput.text.startsWith("-") && textInput.text.isEmpty()) {
            textInput.append((view as Button).text)
            lastNumeric = false
            lastBot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean =
        if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonPlus -> getOperator(v)
            R.id.buttonMinus -> getOperator(v)
            R.id.buttonMultiply -> getOperator(v)
            R.id.buttonDivide -> getOperator(v)
            R.id.buttonEqual -> onAddEqual()
            R.id.buttonClear -> onAddClear()
            R.id.buttonDecimal -> onCheckDecimalPoint()
            R.id.buttonZero -> onAddNumber(v)
            R.id.buttonOne -> onAddNumber(v)
            R.id.buttonTwo -> onAddNumber(v)
            R.id.buttonThree -> onAddNumber(v)
            R.id.buttonFour -> onAddNumber(v)
            R.id.buttonFive -> onAddNumber(v)
            R.id.buttonSix -> onAddNumber(v)
            R.id.buttonSeven -> onAddNumber(v)
            R.id.buttonEight -> onAddNumber(v)
            R.id.buttonNine -> onAddNumber(v)
        }
    }
}

