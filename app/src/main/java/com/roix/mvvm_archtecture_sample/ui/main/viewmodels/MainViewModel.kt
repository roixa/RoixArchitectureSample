package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.ui.common.BaseViewModel
import com.roix.mvvm_archtecture_sample.utils.extensions.setValueNoHistory
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseViewModel() {

    @Inject
    protected lateinit var mainInteractor: IMainInteractor

    override fun doInject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onBindFirstView() {
        super.onBindFirstView()

        mainInteractor.testRequest().sub { s ->
                showMessageDialogLiveData.setValueNoHistory(s)
                requestTestObservableField.set(s)
        }
    }

    val requestTestLiveData by lazy {
        mainInteractor.testRequest().map { t -> t }.subInLiveData()
    }

    val requestTestObservableField by lazy {
        mainInteractor.testRequest().subInObserverbleField()
    }

}