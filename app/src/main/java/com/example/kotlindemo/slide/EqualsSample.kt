package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-17.
 */

fun equalsSample() {
    val name1 = "Вася"
    val name2 = "Петя"
    if (name1 == name2) {
        Log.d("", "names equal")
    }

    if (name1 === name2) {
        Log.d("", "pointers equal")
    }

    val char = 'a'.toString()
    val string = "a"
    if (char == string) {
        Log.d("", "strings equal")
    }

    val intValue = 1
    val longValue = 2L
/*
    if (longValue == intValue) {
        Log.d("", "numbers equal")
    }
*/
}