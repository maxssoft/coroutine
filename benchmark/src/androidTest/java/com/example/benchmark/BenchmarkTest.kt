package com.example.kotlindemo

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BenchmarkTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val lists10 = createLists(10)
    private val lists100 = createLists(100)
    private val lists1000 = createLists(1000)

    fun getVariantIndex() = (0..VARIANTS_COUNT).shuffled().first()

    @Test
    fun list10() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {

            val list = runWithTimingDisabled {
                lists10[getVariantIndex()]
            }
            // val list = runWithTimingDisabled { createList(10) }
            ii += listTest(list)
        }
        log("ii = $ii")
    }
    @Test
    fun list100() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            val list = runWithTimingDisabled {
                lists100[getVariantIndex()]
            }
            ii += listTest(list)
        }
        log("ii = $ii")
    }
    @Test
    fun list1000() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            val list = runWithTimingDisabled {
                lists1000[getVariantIndex()]
            }
            ii += listTest(list)
        }
        log("ii = $ii")
    }

    @Test
    fun sequence10() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {

            val list = runWithTimingDisabled {
                lists10[getVariantIndex()]
            }
            ii += sequenceTest(list)
        }
        log("ii = $ii")
    }
    @Test
    fun sequence100() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            val list = runWithTimingDisabled {
                lists100[getVariantIndex()]
            }
            ii += sequenceTest(list)
        }
        log("ii = $ii")
    }
    @Test
    fun sequence1000() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            val list = runWithTimingDisabled {
                lists1000[getVariantIndex()]
            }
            ii += sequenceTest(list)
        }
        log("ii = $ii")
    }

    private fun log(message: String) {
        print(message)
        // Log.d(TAG, message)
    }

    private fun createLists(count: Int): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        for (i in 0..VARIANTS_COUNT) {
            list.add(createList(count))
        }
        return list
    }

    private fun createList(count: Int): List<Int> {
        val list = mutableListOf<Int>()
        for (i in 0..count) {

            var valueGenerated = false
            while (!valueGenerated) {
                val value = (1..50).shuffled().first()

                valueGenerated = if (value == 33) {
                    !list.contains(value)
                } else {
                    true
                }

                if (valueGenerated) {
                    list.add(value)
                }
            }
        }
        return list
    }

    // first filter.toList
    private fun listTest(list: List<Int>): Int {
        val items = list
            .filter { it > 20 }
            .map { it.toString() }
            .map { it.toInt() }

        return items.firstOrNull() ?: 0
    }

    private fun sequenceTest(list: List<Int>): Int {
        val items = list.asSequence()
            .filter { it > 20 }
            .map { it.toString() }
            .map { it.toInt() }

        return items.toList().firstOrNull() ?: 0
    }

/*
    // last filter.toList
    private fun listTest(list: List<Int>): Int {
        val items = list
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it > 20 }

        return items.firstOrNull() ?: 0
    }

    private fun sequenceTest(list: List<Int>): Int {
        val items = list.asSequence()
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it > 20 }

        return items.toList().firstOrNull() ?: 0
    }
*/

/*
    // DblFilter.toList
    private fun listTest(list: List<Int>): Int {
        val items = list
            .filter { it > 20 }
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it == 33 }

        return items.firstOrNull() ?: 0
    }

    private fun sequenceTest(list: List<Int>): Int {
        val items = list.asSequence()
            .filter { it > 20 }
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it == 33 }

        return items.firstOrNull() ?: 0
        //return items.toList().firstOrNull() ?: 0
    }
*/

/*
    // first
    private fun listTest(list: List<Int>): Int {
        val item = list
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it > 20 }
            .firstOrNull { it == 33 }

        return item ?: 0
    }

    private fun sequenceTest(list: List<Int>): Int {
        val item = list.asSequence()
            .map { it.toString() }
            .map { it.toInt() }
            .filter { it > 20 }
            .firstOrNull { it == 33 }

        return item ?: 0
    }
*/

    companion object {
        const val VARIANTS_COUNT = 10
        const val ITERATION_COUNT = 100

        const val TAG = "sequence"
        val LIST_COUNT = 10
    }

}