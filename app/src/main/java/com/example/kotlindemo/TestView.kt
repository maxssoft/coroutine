package com.example.kotlindemo

/**
 * @author Сидоров Максим on 2019-12-23.
 */
interface TestView {

    fun showProgress()
    fun hideProgress()

    fun updateStatus(status: String)
    fun updateLog(log: String)
}