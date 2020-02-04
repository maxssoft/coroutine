package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-21.
 */

class ByLazySample(runnable: Runnable) {
    private var i: Int = 1
    private val name: String by lazy {
        i = 2
        return@lazy "lazy test" }
}