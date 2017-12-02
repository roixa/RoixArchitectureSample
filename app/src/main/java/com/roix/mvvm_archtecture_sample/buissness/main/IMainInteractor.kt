package com.roix.mvvm_archtecture_sample.buissness.main

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
interface IMainInteractor {
    fun testRequest(): Single<String>
}