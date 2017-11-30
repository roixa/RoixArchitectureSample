package com.roix.mvvm_archtecture_sample.dagger.common

import android.content.Context
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by roix on 29.11.2017.
 */
@Module
@Singleton
class AppModule(val context: Context) {
    @Provides fun provideContext(): Context = context
}