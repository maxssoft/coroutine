package com.example.kotlindemo.slide

import androidx.annotation.Nullable
import java.lang.StringBuilder
import kotlin.math.min

/**
 * @author Сидоров Максим on 2020-01-20.
 */

fun String?.isEmpty(): Boolean {
    return this == null || this.length == 0
}

fun String.hasSubstring(substring: String): Boolean {
    val i = this.indexOf(substring)
    return i != -1
}

fun Runnable.myRun() {
    this.run()
}

typealias MyFunc = (Int) -> Int

fun MyFunc.runFunc(value: Int) {
    this.invoke(value)
}

class ExtentionSample {

    fun getName(): String {
        return "Class"
    }

    fun ExtentionSample.getName(): String {
        return "Function"
    }

    fun test() {
        getName()
    }
}