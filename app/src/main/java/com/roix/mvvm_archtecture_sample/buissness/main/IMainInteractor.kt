package com.roix.mvvm_archtecture_sample.buissness.main

import com.roix.mvvm_archtecture_sample.buissness.common.IBaseInteractor
import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.toothpick.main.MainViewModelScope
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
@MainViewModelScope
interface IMainInteractor :IBaseInteractor, IBaseListInteractor<ThreadItem> {
    fun testRequest(): Single<String>

    fun getThreads(page:Int): Single<List<ThreadItem>>
}