package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-20.
 */

enum class NumberType {EMPTY, NEGATIVE, ZERO, POSITIVE}

fun whenTest(value: Int): String {
    return when {
        value < 0 -> "negative"
        value > 0 -> "positive"
        value == 0 -> "zero"
        else -> "nonsense"
    }
}

fun whenTestBool(value: Boolean): String {
    return when(value) {
        true -> "true"
        false -> "false"
    }
}

fun whenTestEnum(value: NumberType): String {
    return when(value) {
        NumberType.ZERO -> "zero"
        NumberType.NEGATIVE -> "negative"
        NumberType.POSITIVE -> "positive"
        NumberType.EMPTY -> "empty"
    }
}
