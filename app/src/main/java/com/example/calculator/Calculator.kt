package com.example.calculator

import com.github.keelar.exprk.Expressions
import java.lang.Exception

class Calculator {
    private var evalString = ""
    private var result = ""

    fun getResult(): String{
        return this.result
    }
    fun getEvalString(): String{
        return this.evalString
    }

    fun evaluate(){
        try {
            this.result = Expressions().eval(this.evalString).toString()
            this.evalString = this.result
        }catch (e:Exception){
            this.result = "Error"
            this.evalString = ""
        }
    }

    fun addValue(symbol:Char){
        this.evalString += symbol
    }

    fun delete(){
        this.evalString = this.evalString.substring(0, this.evalString.length-1)
    }

    fun clear(){
        this.evalString = ""
        this.result = ""
    }
}
