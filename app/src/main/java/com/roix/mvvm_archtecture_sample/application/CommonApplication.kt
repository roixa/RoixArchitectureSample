package com.roix.mvvm_archtecture_sample.application

import android.app.Application
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.dagger.common.AppModule
import com.roix.mvvm_archtecture_sample.dagger.common.CommonModule
import com.roix.mvvm_archtecture_sample.dagger.common.DaggerAppComponent
import com.roix.mvvm_archtecture_sample.dagger.main.MainModule

/**
 * Created by roix on 29.11.2017.
 */
class CommonApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent:AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}