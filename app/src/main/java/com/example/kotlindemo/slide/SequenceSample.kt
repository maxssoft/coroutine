package com.example.kotlindemo.slide

import android.util.Log
import kotlin.math.min

/**
 * @author Сидоров Максим on 2020-02-03.
 */

var ii = 0

private val listCount: Int
    get() {
        return when(tryNumber) {
            1 -> 10
            2 -> 100
            else -> 1000
        }
    }

private var tryNumber = 1

const val LIST_COUNT = 100

const val ITERATION_COUNT = 100
const val TAG = "sequence test" 

class TimeMeter {
    private var time: Long = 0L
    var count: Int = 0
        private set

    val avgTime: Long
        get() = time / count

    fun addTime(time: Long) {
        count++
        this.time = this.time + time
    }

    fun clear() {
        count = 0
        time = 0L
    }
}

var sequenceTimeMeter = TimeMeter()
var listTimeMeter = TimeMeter()

fun testsequence() {
    Log.d(TAG, "test started, count $listCount")

    sequenceTimeMeter.clear()
    listTimeMeter.clear()
    for (i in 1..ITERATION_COUNT) {
        val list = createList(listCount)
        ii += sequenceTest(list)
        ii += listTest(list)
        if (i % 10 == 0) {
            Log.d(TAG, "count $i")
        }
    }
    Log.d(TAG, "test finished.........................")
    Log.d(TAG, "list count = $listCount")
    Log.d(TAG, "tryNumber = $tryNumber")

    tryNumber++
    if (tryNumber > 3) {
        tryNumber = 1
    }


/*
    Log.d(TAG, "sequence avg  = ${sequenceTimeMeter.avgTime}")
    Log.d(TAG, "list avg  = ${listTimeMeter.avgTime}")
*/
    Log.d(TAG, "ii  = $ii")
}

fun createList(count: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0..count) {
        list.add((1..50).shuffled().first())
    }
    return list
}

/*
fun listTest(list: List<Int>): Int {
    var i = 0
    val items = list
        .filter {
            it > 20
        }
        .map {
            it.toString()
        }
        .map {
            it.toInt()
        }

    return items.firstOrNull() ?: 0
}

fun sequenceTest(list: List<Int>): Int {
    var i = 0
    val items = list.asSequence()
        .filter {
            it > 20
        }
        .map {
            it.toString()
        }
        .map {
            it.toInt()
        }
        .toList()

    return items.firstOrNull() ?: 0
}
*/

fun listTest(list: List<Int>): Int {
    val items = list
        .filter {
            it > 20
        }
        .map {
            it.toString()
        }
        .map {
            it.toInt()
        }
        .filter { it == 33 }

    return items.firstOrNull() ?: 0
}

fun sequenceTest(list: List<Int>): Int {
    val items = list.asSequence()
        .filter {
            it > 20
        }
        .map {
            it.toString()
        }
        .map {
            it.toInt()
        }
        .filter { it == 33 }

    return items.toList().firstOrNull() ?: 0
}

/*
fun listTest(list: List<Int>): Int {
    val item = list
        .filter { it > 20 }
        .map { it.toString() }
        .map { it.toInt() }
        .firstOrNull { it == 33 }

    return item ?: 0
}

fun sequenceTest(list: List<Int>): Int {
    val item = list.asSequence()
        .filter { it > 20 }
        .map { it.toString() }
        .map { it.toInt() }
        .firstOrNull { it == 33 }

    return item ?: 0
}
*/
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
