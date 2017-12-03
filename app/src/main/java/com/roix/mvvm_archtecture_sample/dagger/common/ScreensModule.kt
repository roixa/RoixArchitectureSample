package com.roix.mvvm_archtecture_sample.dagger.common

import com.roix.mvvm_archtecture_sample.dagger.main.MainModule
import dagger.Module

/**
 * Created by roix on 28.11.2017.
 */
@ViewModelScope
@Module(includes = arrayOf(
        MainModule::class
))
class ScreensModule
