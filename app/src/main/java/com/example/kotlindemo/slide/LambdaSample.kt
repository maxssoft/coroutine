package com.example.kotlindemo.slide

import android.os.Handler

/**
 * @author Сидоров Максим on 2020-01-20.
 */

fun testLambda() {
    var localValue = 1
    val runnable = Runnable {
        localValue = 2
    }


}

fun tt(f: (Int) -> Int): Int {
    return f.invoke(0)
}


fun ttt() {
    tt { it + 1 }
}