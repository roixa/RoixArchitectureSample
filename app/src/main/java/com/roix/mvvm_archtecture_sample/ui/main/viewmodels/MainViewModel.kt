package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseListViewModel
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseListViewModel<ThreadItem>() {

    @Inject
    protected lateinit var mainInteractor: MainInteractor

    override fun getInteractor(): IBaseListInteractor<ThreadItem> = mainInteractor

    override fun doInject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    val requestTestLiveData by lazy {
        mainInteractor.testRequest().map { t -> t }.subInLiveData()
    }

    val requestTestObservableField by lazy {
        mainInteractor.testRequest().subInObserverbleField()
    }

}