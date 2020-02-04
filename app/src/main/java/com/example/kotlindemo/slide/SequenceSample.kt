package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-02-03.
 */

var s: String? = null

fun testsequence() {
    var startTime = 0L
    var finishTime = 0L
    var time = 0L
    val list = createList(1000)
    val count = 10000

    startTime = System.currentTimeMillis()
    Log.d("time", "list started at $startTime")
    for (i in 1..count) {
        s = listTest(list)
    }
    finishTime = System.currentTimeMillis()
    time = finishTime - startTime
    Log.d("time", "list finished at $finishTime")
    Log.d("time", "list time = $time")


    startTime = System.currentTimeMillis()
    Log.d("time", "sequence started at $startTime")
    for (i in 1..count) {
        s = sequenceTest(list)
    }
    finishTime = System.currentTimeMillis()
    time = finishTime - startTime
    Log.d("time", "sequence finished at $finishTime")
    Log.d("time", "sequence time = $time")
}

fun createList(count: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0..count) {
        list.add((1..50).shuffled().first())
    }
    return list
}


fun listTest(list: List<Int>): String? {
    val item = list
        .filter { it > 2 }
        .map { it.toString() }
        .firstOrNull { it == "3" }

    return item
}

fun sequenceTest(list: List<Int>): String? {
    val item = list.asSequence()
        .filter { it > 2 }
        .map { it.toString() }
        .firstOrNull { it == "3" }

    return item
}

/*
fun sequenceTest(): String? {
    var iii = 1
    val list = listOf(1, 2, 3)

    val item = list.asSequence()
        .filter {
            iii = iii + 1
            it > 2
        }
        .map {
            iii = iii + 1
            it.toString()
        }
        .firstOrNull {
            iii = iii + 1
            it == "3"
        }

    return item
}
*/
