package com.example.kotlindemo.slide

import android.os.Handler
import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-31.
 */
var i = 0


fun check(checker: () -> Unit) {
    Log.d("", "run check")
    checker.invoke()
}

/*
inline fun String.checkItem(noinline checker: () -> Unit) {
    Log.d("", "run checkItem")
    check(checker)
}

fun testInline() {
    var ii = 1
    val list = listOf("1", "2")

    for (item in list) {
        item.checkItem {
            i = ii - 1
            Log.d("", "run lambda")
        }
    }
}
*/

inline fun String.checkItem(noinline checker: () -> Unit) {
    Log.d("", "run checkItem")
    Handler().post { checker.invoke() }
}

fun testInline1() {
    var i = 1
    val list = listOf("1", "2")

    for (item in list) {
        item.checkItem {
            i = i + 1
            Log.d("", "run lambda")
        }
    }
}
