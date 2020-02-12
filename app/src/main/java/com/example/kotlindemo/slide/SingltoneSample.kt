package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-20.
 */

object SingletonSample {
    const val constant = 1
    val value = 2

    fun <T: String> tst(value: T): T? {
        return value.toString() as? T
    }
}

class CompanionSampleClass {

    companion object {
        val value = 0
        var variable = 1
        const val constant = 2

        @JvmStatic
        fun ff() {

        }
    }
}


fun test() {
    CompanionSampleClass.variable = CompanionSampleClass.value + CompanionSampleClass.constant
}