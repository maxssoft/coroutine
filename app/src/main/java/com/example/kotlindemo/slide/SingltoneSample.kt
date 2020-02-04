package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-20.
 */

object SingletonSample {
    const val constant = 1
    val value = 2
}

class CompanionSampleClass {

    companion object {
        val value = 0
        var variable = 1
        const val constant = 2
    }
}


fun test() {
    CompanionSampleClass.variable = CompanionSampleClass.value + CompanionSampleClass.constant
}