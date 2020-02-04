package com.example.kotlindemo.slide

import android.util.Log

/**
 * @author Сидоров Максим on 2020-01-24.
 */

open class SampleClass {
    open var name: String? = ""
        set(value) {
            Log.d("", "name change")
            field = value
        }
        get() {
            Log.d("", "name read")
            return field
        }
}

class SampleSubClass : SampleClass() {
    override var name: String?
        get() {
            Log.d("", "sub.name read")
            return super.name
        }
        set(value) {
            Log.d("", "sub.name change")
            super.name = value
        }
}