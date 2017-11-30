package com.roix.mvvm_archtecture_sample.dagger.main

import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.ViewModelScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by roix on 29.11.2017.
 */
@Singleton
@Module
class MainModule{
    @Provides fun provideInteractor():IMainInteractor=MainInteractor()
}