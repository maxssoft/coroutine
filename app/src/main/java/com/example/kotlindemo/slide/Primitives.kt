package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-17.
 */

fun testPrimitives() {
    val value: Int = 0
    value.equals(2)
    val nullableValue: Int? = null

    val values = listOf<Int>(1, 2, 3)
    var item: Int = values[0]

    val nullableValues = listOf<Int?>(1, 2, 3, null)
    item = nullableValues[0] ?: 0


    val a = value + (nullableValue ?: 0)
}

fun testPrmitiveList() {
    val intList = intArrayOf(1, 2, 3)
    val byteList = byteArrayOf(1, 2)
}

fun testPrimitives2(){
    val v: Int? = 0
    val b = (v ?: 0) + 1
}