package com.roix.mvvm_archtecture_sample.application

import android.app.Application
import com.roix.mvvm_archtecture_sample.toothpick.common.ApplicationScope
import com.roix.mvvm_archtecture_sample.toothpick.test.TopLevelClaccTwo
import toothpick.Toothpick

/**
 * Created by roix on 29.11.2017.
 */
class CommonApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        val scope=Toothpick.openScope(this)
        scope.bindScopeAnnotation(ApplicationScope::class.java)
        TopLevelClaccTwo()
    }
}