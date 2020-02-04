package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-16.
 */
class NullSampleClass {
    var name: String = ""

    private fun checkName(value: String): Boolean {
        return value.isNotBlank()
    }
}

class InnerNullSampleClass {
    private var name: String = "Вася"

    fun test() {
        name = "Вася Пупкин".substring(0, 4)
    }

    fun getName(): String = "Петя"

    private fun testParams(value: String): String {
        return value.substring(4) + getName()
    }
}

fun concat(s1: String, s2: String): String {
    return s1 + s2
}

fun sum(v1: Int?, v2: Int?): Int {
    return (v1 ?: 0) + (v2!!)
}

fun sum1(v1: Int?, v2: Int?): Int {
    return v1?.plus(v2 ?: 0) ?: v2 ?: 0
}

fun concat1(s1: String?, s2: String?): String {
    return s1?.plus(s2) ?: ""
}

fun left(value: String?, count: Int): String? {
    return value?.substring(count)
}

fun leftOrNull(value: StringWrapper?, count: Int): String? {
    return value?.substring(count)
}
