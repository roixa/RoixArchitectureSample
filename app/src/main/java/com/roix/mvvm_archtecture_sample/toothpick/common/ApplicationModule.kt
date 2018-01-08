package com.roix.mvvm_archtecture_sample.toothpick.common

import android.content.Context
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.utils.rx.general.RxSchedulers
import com.roix.mvvm_archtecture_sample.utils.rx.general.RxSchedulersAbs
import toothpick.config.Module

/**
 * Created by roix on 08.01.2018.
 */
class ApplicationModule(val application: CommonApplication):Module() {
    init {
        bind(Context::class.java).toInstance(application.applicationContext)
        bind(RxSchedulersAbs::class.java).toInstance(RxSchedulers())
    }
}