package com.example.kotlindemo

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.rule.GrantPermissionRule
import org.junit.Before


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BenchmarkFieldTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    val testObject = TestGetter()

    @Test
    fun fieldTest() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            runWithTimingDisabled {
                testObject.prepare(i)
            }
            ii += testObject._getFieldTest()
        }
        log("ii = $ii")
    }

    @Test
    fun _fieldTest() = benchmarkRule.measureRepeated {
        var ii = 0
        for (i in 1..ITERATION_COUNT) {
            runWithTimingDisabled {
                testObject.prepare(i)
            }
            ii += testObject.getFieldTest()
        }
        log("ii = $ii")
    }

    private fun log(message: String) {
        print(message)
        // Log.d(TAG, message)
    }


    companion object {
        const val ITERATION_COUNT = 100000
    }

    class TestGetter {

        private var _field: Int = 0
        var field: Int = 0


        fun prepare(newValue: Int) {
            _field = newValue
            field = newValue
        }

        fun getFieldTest(): Int {
            return field
        }
        fun _getFieldTest(): Int {
            return _field
        }
    }


}