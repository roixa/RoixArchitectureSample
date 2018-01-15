package com.roix.mvvm_archtecture_sample.buissness.simple

import io.reactivex.Single

/**
 * Created by roix on 06.01.2018.
 */
interface ISimpleInteractor{
    fun testRequest(): Single<String>

}