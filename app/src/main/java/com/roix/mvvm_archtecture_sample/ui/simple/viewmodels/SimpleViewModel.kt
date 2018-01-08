package com.roix.mvvm_archtecture_sample.ui.simple.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.simple.ISimpleInteractor
import com.roix.mvvm_archtecture_sample.toothpick.simple.SimpleModule
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseDatabindingViewModel
import toothpick.config.Module
import javax.inject.Inject

/**
 * Created by roix on 06.01.2018.
 */
class SimpleViewModel :BaseDatabindingViewModel() {

    @Inject
    protected lateinit var simpleInteractor :ISimpleInteractor

    override fun getModule(): Module = SimpleModule()
}