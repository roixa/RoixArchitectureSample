package com.roix.mvvm_archtecture_sample.dagger.simple

import com.roix.mvvm_archtecture_sample.buissness.simple.ISimpleInteractor
import com.roix.mvvm_archtecture_sample.buissness.simple.SimpleInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.ViewModelScope
import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import dagger.Module
import dagger.Provides

/**
 * Created by roix on 06.01.2018.
 */

@ViewModelScope
@Module
class SimpleModule {
    @Provides
    fun provideInteractor(serverRepository: IServerRepository)
            : ISimpleInteractor = SimpleInteractor(serverRepository)

}