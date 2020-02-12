package com.example.kotlindemo.slide

/**
 * @author Сидоров Максим on 2020-02-07.
 */

interface ToggleDelegate {
    val featureOneEnabled: Boolean
    val featureTwoEnabled: Boolean
}

class ToggleDelegateImpl : ToggleDelegate {
    override val featureOneEnabled: Boolean
        get() = true
    override val featureTwoEnabled: Boolean
        get() = false
}

interface FeatureRepository : ToggleDelegate {
    fun someMethod(): Boolean
}

class FeatureRepositoryImpl(private val toggleDelegate: ToggleDelegate) : FeatureRepository, ToggleDelegate by toggleDelegate {

    override fun someMethod(): Boolean {
        return true
    }
}