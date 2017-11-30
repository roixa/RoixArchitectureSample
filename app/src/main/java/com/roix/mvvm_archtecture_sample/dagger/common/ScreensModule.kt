package com.roix.mvvm_archtecture_sample.dagger.common

import com.roix.mvvm_archtecture_sample.dagger.main.MainModule
import dagger.Module
import javax.inject.Singleton

/**
 * Created by roix on 28.11.2017.
 */
@Singleton
@Module(includes = arrayOf(
        MainModule::class
))
class ScreensModule
