package com.roix.mvvm_archtecture_sample.ui.simple.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.simple.ISimpleInteractor
import com.roix.mvvm_archtecture_sample.toothpick.simple.SimpleModule
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseLifecycleViewModel
import toothpick.config.Module
import javax.inject.Inject

/**
 * Created by roix on 06.01.2018.
 */
class SimpleViewModel :BaseLifecycleViewModel() {

    @Inject
    protected lateinit var simpleInteractor :ISimpleInteractor

    override fun getModule(): Module = SimpleModule()

    val testResponse by lazy {
        simpleInteractor.testRequest().toLiveData()
    }
}