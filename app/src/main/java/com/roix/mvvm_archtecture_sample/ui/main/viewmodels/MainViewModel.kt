package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.toothpick.main.MainModule
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseListViewModel
import toothpick.config.Module
import javax.inject.Inject


/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseListViewModel<ThreadItem>() {

    @Inject lateinit var interactor: IMainInteractor

    override fun getInteractor(): IBaseListInteractor<ThreadItem> {
        return interactor
    }


    override fun getModule(): Module = MainModule()

    val requestTestLiveData by lazy {
        interactor.testRequest().map { t -> t }.subInLiveData()
    }

    val requestTestObservableField by lazy {
        interactor.testRequest().subInObserverbleField()
    }

    override fun onBindFirstView() {
        super.onBindFirstView()
        interactor.testRequest().sub { s -> }
        showMessageDialogLiveData.value="saas"
        showMessageDialogLiveData.setValueNoHistory("sasd")
        showMessageDialogLiveData.postValue("dadad")
    }
}