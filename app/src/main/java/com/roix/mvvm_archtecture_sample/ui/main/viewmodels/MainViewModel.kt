package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.ui.common.BaseViewModel
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseViewModel(), IMainViewModel {
    @Inject
    lateinit var mainInteractor: MainInteractor

    init {
        CommonApplication.appComponent.inject(this)
    }
}