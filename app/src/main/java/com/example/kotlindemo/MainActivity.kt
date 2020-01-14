package com.example.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), TestView {

    private val presenter = Presenter()

    private lateinit var statusView: TextView
    private lateinit var logView: TextView
    private lateinit var loaderView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun initView() {
        statusView = findViewById(R.id.status_text_view)
        logView = findViewById(R.id.log_text_view)
        loaderView = findViewById(R.id.loader_view)

        findViewById<Button>(R.id.start_simple).setOnClickListener {
            presenter.loadSimple()
        }
        findViewById<Button>(R.id.start_zip).setOnClickListener {
            presenter.loadWithZip()
        }
        findViewById<Button>(R.id.start_error).setOnClickListener {
            presenter.loadWithError()
        }
        findViewById<Button>(R.id.start_flatmap).setOnClickListener {
            presenter.loadFlatMap()
        }
        findViewById<Button>(R.id.start_long).setOnClickListener {
            presenter.loadLongTask()
        }
        findViewById<Button>(R.id.dismiss_button).setOnClickListener {
            presenter.cancel()
        }
        findViewById<Button>(R.id.start_rx).setOnClickListener {
            presenter.loadRx(StepList(STEP_COUNT))
        }
        findViewById<Button>(R.id.start_coroutine).setOnClickListener {
            presenter.loadCoroutine(StepList(STEP_COUNT))
        }

        presenter.attachView(this)
    }

    override fun showProgress() {
        loaderView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loaderView.visibility = View.GONE
    }

    override fun updateStatus(status: String) {
        statusView.text = status
    }

    override fun updateLog(log: String) {
        logView.text = log
    }

}

const val STEP_COUNT = 1000
