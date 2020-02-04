package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-21.
 */

fun sort(list: List<Int>, compareFunc: (Int, Int) -> Int) {
    list.sortedWith(
        Comparator { o1, o2 ->
            compareFunc.invoke(o1, o2)
        }
    )
}

fun testCompare() {
    var i: Int = 0

    val compareFunc: (Int, Int) -> Int = { o1, o2 ->
        i = i + 1
        when {
            o1 > o2 -> 1
            o1 < o2 -> -1
            else -> 0
        }
    }
    i = compareFunc.invoke(1, 2)

    val list = listOf(1, 2, 3)
    sort(list, compareFunc)
    i = 1


    val f: (Int, Int) -> Int = {o1, o2 ->
        i = i + 1
        o1 + o2
    }
    val f1: (Int) -> Int = {it ->
        i = i + it
        it
    }
    val f2: () -> Int = { i
    }


    val ff1: (Int, Int) -> Int = { o1, o2 -> o1 + o2 }
    val ff2: (Int) -> Int = { it -> it + 1}
    val ff3: () -> Unit = { Log.d("", "ку ку") }

}
