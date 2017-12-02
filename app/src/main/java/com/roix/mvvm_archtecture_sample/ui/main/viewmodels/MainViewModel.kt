package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.ui.common.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseViewModel(), IMainViewModel {

    @Inject
    protected lateinit var mainInteractor: IMainInteractor

    val requestTest by lazy {
        mainInteractor.testRequest().toLiveData()
    }

    override fun doInject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onBindFirstView() {
        super.onBindFirstView()

        mainInteractor.testRequest().sub { s ->
            run {
                showMessageDialogLiveData.postValue(s)
            }
        }
    }

    override fun getTestRequest(): Single<String> = mainInteractor.testRequest()


}