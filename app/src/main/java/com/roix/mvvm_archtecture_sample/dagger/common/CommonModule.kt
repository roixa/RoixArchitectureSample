package com.roix.mvvm_archtecture_sample.dagger.common

import dagger.Module
import javax.inject.Singleton
import kotlin.reflect.KClass

/**
 * Created by roix on 29.11.2017.
 */


@Module(includes = arrayOf(
        ScreensModule::class
        ,
        RepositoriesModule::class
))
@Singleton
class CommonModule