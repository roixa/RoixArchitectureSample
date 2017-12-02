package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
interface IMainViewModel {
    fun getTestRequest(): Single<String>
}