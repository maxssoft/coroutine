package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-01-20.
 */
// Объявление алиаса на пакет
import java.util.ArrayList as JavaList

// Объявление алиаса на класс
typealias JavaIntList = JavaList<Int>

fun createList(): JavaIntList {
    return mutableListOf(1, 2, 3) as JavaIntList
}

// Объявление алиаса на функциональный тип
typealias SumFunc = (JavaIntList) -> Int

// для алиаса можно объявлять функции раширений
fun JavaIntList.sum(summator: SumFunc): Int {
    return summator.invoke(this)
}