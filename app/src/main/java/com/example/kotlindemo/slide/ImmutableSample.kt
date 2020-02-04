package com.example.kotlindemo.slide

import android.util.Log
import java.util.*

/**
 * @author Сидоров Максим on 2020-01-18.
 */

class ImmutableSample {
    val value: Int = 1
    var variable: Int = 1
    val name: String
        get() {
            return "Вася"
        }

    fun test(): Int {
        val value: Int? = null
        var variable: Int? = 1
        val s = "sws"
        return value!! + variable!!
    }
}

fun immutableTest() {
    val list = listOf(1, 2, 3)
    var mutableList = mutableListOf(1, 2, 3)

    val javaList = list as java.util.AbstractList<Int>
    javaList.add(4)

    mutableList.addAll(list)
}

fun listAsTest() {
    val list = listOf(1, 2, 3)
    val javaList = list as AbstractList<Int>

    list[0] = 4
    list[0] = null

    javaList.set(0, 4) // success
    javaList.set(0, null)

    val i: Int = list.get(0)
    Log.d("", "i = $i")
    javaList.add(4)    // runtime error - abstract method
}

fun testLocalVal() {
    val a = 1
    Runnable {
        Log.d("", "$a")
    }
}
