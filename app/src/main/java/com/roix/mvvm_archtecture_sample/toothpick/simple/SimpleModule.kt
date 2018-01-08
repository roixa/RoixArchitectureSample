package com.roix.mvvm_archtecture_sample.toothpick.simple

import com.roix.mvvm_archtecture_sample.buissness.simple.ISimpleInteractor
import com.roix.mvvm_archtecture_sample.buissness.simple.SimpleInteractor
import toothpick.config.Module

/**
 * Created by roix on 08.01.2018.
 */
class SimpleModule : Module() {
    init {
        bind(ISimpleInteractor::class.java).to(SimpleInteractor::class.java).instancesInScope()
    }
}