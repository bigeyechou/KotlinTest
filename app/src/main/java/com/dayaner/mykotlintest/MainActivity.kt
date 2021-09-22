package com.dayaner.mykotlintest

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    fun breakTest(){

       abcdefg@ for (out in 1..5){
            if ( out == 2) return@abcdefg
        }

        val intArray = intArrayOf(1,2,3,4,5,6)
        intArray.forEach {
            if (it == 2)return@forEach
        }

    }

    fun xiecheng (){

        GlobalScope.launch {
            delay(1000)

        }
    }

}