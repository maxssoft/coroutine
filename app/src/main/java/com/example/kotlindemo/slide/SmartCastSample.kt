package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-27.
 */

fun testSmatCast() {
    val list = listOf(1, 2 ,3)
    //list.add(4) // compile error

    if (list is MutableList<Int>) {
        list.add(4)
    }
    //list.add(4) // compile error

    val l1 = list as? MutableList<Int?>
    l1?.add(4)
    //list.add(4) // compile error

    val l2 = list as MutableList<Int>
    l2.add(4)
    list.add(4) // compile ok
}

fun testSmatCast2() {
    val list = listOf(1, 2, 3)

    val l2 = list as MutableList<Int>
    l2.add(4)
    list.add(4) // compile ok
}

fun testSmatCast3() {
    var name = "Вася"

    val s = name as Int

    // обращаемся к приведенному типу
    val b = name + 1

    // обращаемся к исходному типу
    val c = name + " Пупкин"
}