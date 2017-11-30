package com.roix.mvvm_archtecture_sample.dagger.common

import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by roix on 28.11.2017.
 */
@Module
@Singleton
class RepositoriesModule {
    @Provides fun provideServerRepository(): IServerRepository = ServerRepository()
}