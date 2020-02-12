package com.example.kotlindemo.slide

import android.os.Handler
import android.util.Log
import java.util.*

/**
 * @author Сидоров Максим on 2020-01-21.
 */

fun testSAM() {
    var i = 1
    Handler().post { Log.d("", "SAM convertation") }
    //Handler().myPost { Log.d("", "no SAM") }
    Handler().myPost( Runnable { Log.d("", "no SAM")
        i = i + 1} )
}

// Вызов Runnable через kotlin
fun Handler.myPost(runnable: Runnable) {
    this.post(runnable)
}

fun samErrorSample() {
    var i = 1

    val listeners = Listeners()

    val listener = Listener {
        Log.d("", "onEvent")
        i = i + 1
    }


    listeners.add { listener }

    listeners.remove { listener}

    listeners.add { Log.d("", "onEvent") }
}



