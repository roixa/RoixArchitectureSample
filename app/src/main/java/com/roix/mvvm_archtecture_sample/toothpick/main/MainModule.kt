package com.roix.mvvm_archtecture_sample.toothpick.main

import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import toothpick.config.Module

/**
 * Created by roix on 08.01.2018.
 */
class MainModule : Module() {
    init {
        bind(IMainInteractor::class.java).to(MainInteractor::class.java).instancesInScope()
    }
}