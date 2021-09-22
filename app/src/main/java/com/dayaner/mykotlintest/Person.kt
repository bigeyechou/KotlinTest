package com.dayaner.mykotlintest

import android.util.Log
import kotlin.math.log

/**
 * -------------------------------------
 * 作者：dayaner
 * -------------------------------------
 * 时间：6/22/21 10:39 AM
 * -------------------------------------
 * 描述：
 * -------------------------------------
 * 备注：
 * -------------------------------------
 */
class Person private constructor(name:String){
    val BIG_NAME = "大名：$name"
    init {
        Log.i(BIG_NAME, ": ")
        var a = 0
        var b = 1
        val max = if (a>b) a else b
    }
}