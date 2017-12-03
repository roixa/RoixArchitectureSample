package com.roix.mvvm_archtecture_sample.dagger.common

import com.roix.mvvm_archtecture_sample.ui.main.viewmodels.MainViewModel
import dagger.Component

/**
 * Created by roix on 28.11.2017.
 */
@ViewModelScope
@Component(modules = arrayOf(CommonModule::class))
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}