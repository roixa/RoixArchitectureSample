package com.roix.mvvm_archtecture_sample.application

import android.app.Application
import android.util.Log
import com.roix.mvvm_archtecture_sample.BuildConfig
import com.roix.mvvm_archtecture_sample.FactoryRegistry
import com.roix.mvvm_archtecture_sample.MemberInjectorRegistry
import com.roix.mvvm_archtecture_sample.toothpick.common.ApplicationModule
import com.roix.mvvm_archtecture_sample.toothpick.common.ApplicationScope
import com.roix.mvvm_archtecture_sample.toothpick.test.TopLevelCkass
import toothpick.Toothpick
import toothpick.Toothpick.setConfiguration
import toothpick.configuration.Configuration.forDevelopment
import toothpick.configuration.Configuration.forProduction
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator
import toothpick.smoothie.module.SmoothieApplicationModule

/**
 * Created by roix on 29.11.2017.
 */
class CommonApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        val configuration = if (BuildConfig.DEBUG) forDevelopment() else forProduction()
        setConfiguration(configuration.disableReflection())
        FactoryRegistryLocator.setRootRegistry(FactoryRegistry())
        MemberInjectorRegistryLocator.setRootRegistry(MemberInjectorRegistry())

        val appScope = Toothpick.openScope(this)
        appScope.installModules(SmoothieApplicationModule(this),ApplicationModule(this))
        appScope.bindScopeAnnotation(ApplicationScope::class.java)
        Toothpick.inject(TopLevelCkass(),appScope)
    }
}