package com.roix.mvvm_archtecture_sample.dagger.main

import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.ViewModelScope
import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import dagger.Module
import dagger.Provides

/**
 * Created by roix on 29.11.2017.
 */
@ViewModelScope
@Module
class MainModule {
    @Provides
    fun provideInteractor(serverRepository: IServerRepository)
            : IMainInteractor = MainInteractor(serverRepository)
}