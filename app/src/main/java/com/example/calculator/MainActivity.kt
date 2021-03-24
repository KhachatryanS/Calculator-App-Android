package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var calc:Calculator = Calculator()
    lateinit var plusButton:Button
    lateinit var minusButton:Button
    lateinit var mulButton:Button
    lateinit var divButton:Button
    lateinit var dotButton: Button
    lateinit var equalButton:Button
    lateinit var modButton:Button
    lateinit var delButton:Button
    lateinit var clearButton:Button

    lateinit var scrollView:HorizontalScrollView
    lateinit var resultTextView:TextView

    lateinit var numberButtons:List<Button>

    private fun initialize(){
        numberButtons = listOf<Button>(
                findViewById(R.id.zero),
                findViewById(R.id.one),
                findViewById(R.id.two),
                findViewById(R.id.three),
                findViewById(R.id.four),
                findViewById(R.id.five),
                findViewById(R.id.six),
                findViewById(R.id.seven),
                findViewById(R.id.eight),
                findViewById(R.id.nine)
        )
        plusButton = findViewById(R.id.plus)
        minusButton = findViewById(R.id.minus)
        mulButton = findViewById(R.id.mul)
        divButton = findViewById(R.id.divide)
        dotButton = findViewById(R.id.dot)
        equalButton = findViewById(R.id.equal)
        modButton = findViewById(R.id.mod)
        delButton = findViewById(R.id.delete)
        clearButton = findViewById(R.id.clear)

        scrollView = findViewById(R.id.resultScroll)
        resultTextView = findViewById(R.id.result)
    }

    private fun addMathOp(op:Char){
        val evalString = calc.getEvalString()
        if(evalString != "" && evalString != "-") {
            if(evalString.last() in listOf('+', '-', '/', '*', '%'))
                calc.delete()
            calc.addValue(op)
        }else if(evalString == "" && op == '-') {
            calc.addValue('-')
        }
        resultTextView.text = calc.getEvalString()
    }

    private fun scroll(){
        scrollView.fullScroll(View.FOCUS_RIGHT)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        //setting onClickListener to all numbers
        for (i in numberButtons.indices){
            numberButtons[i].setOnClickListener {
                if(calc.getEvalString() == calc.getResult()){                           // if equal button is clicked before
                    calc.clear()                                                        // adding next number clear TextView
                    resultTextView.text = calc.getEvalString()
                }

                calc.addValue((i + 48).toChar())        // 48 -> ASCII Code of '0'
                resultTextView.text = calc.getEvalString()
                scroll()
            }
        }

        plusButton.setOnClickListener {
            addMathOp('+')
            scroll()
        }
        minusButton.setOnClickListener {
            addMathOp('-')
            scroll()
        }
        mulButton.setOnClickListener {
            addMathOp('*')
            scroll()
        }
        divButton.setOnClickListener {
            addMathOp('/')
            scroll()
        }
        modButton.setOnClickListener {
            addMathOp('%')
            scroll()
        }
        clearButton.setOnClickListener {
            calc.clear()
            resultTextView.text = calc.getEvalString()
        }
        delButton.setOnClickListener {
            if(calc.getEvalString() != "")
                calc.delete()
            resultTextView.text = calc.getEvalString()
            scroll()
        }

        dotButton.setOnClickListener {

            val evalString = calc.getEvalString()

            if(evalString != ""){                                                       // if line is not empty and
                if(evalString.last() != '.' && evalString.last() !in ('0'..'9')){       // if the line doesn't end with '.' or number
                    calc.addValue('0')                                          // then add "0."
                    calc.addValue('.')
                }else if(evalString.last() in ('0'..'9')){                              // if it ends with number then just add '.'
                    calc.addValue('.')
                }
            }else{                                                                      // if line is empty add "0."
                calc.addValue('0')
                calc.addValue('.')
            }
            resultTextView.text = calc.getEvalString()
        }

        equalButton.setOnClickListener {
            calc.evaluate()
            resultTextView.text = calc.getResult()
        }
    }
}