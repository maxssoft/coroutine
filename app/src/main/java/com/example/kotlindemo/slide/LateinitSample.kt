package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-21.
 */

class LateinitSample {
    lateinit var name: String
        private set

    val displayName: String
        get() {
            return "Имя: " + name
        }

    fun init() {
        name = "lateinit"
    }
}

fun testleLateinit() {
    val c = LateinitSample()
    c.init()
}