package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.toothpick.main.MainModule
import com.roix.mvvm_archtecture_sample.toothpick.main.MainViewModelScope
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseListViewModel
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseViewModel
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

/**
 * Created by roix on 30.11.2017.
 */
@MainViewModelScope
class MainViewModel : BaseListViewModel<ThreadItem>() {

    protected lateinit var interactor:MainInteractor

    override fun getInteractor(): IBaseListInteractor<ThreadItem> {
        return interactor
    }


    override fun doInject(scope: Scope) {
    }

    override fun getModule(): Module = MainModule()

    override fun getViewModelScope(): Class<BaseViewModel> = this.javaClass

    override fun proceedInject(application: CommonApplication) {
        val scope = Toothpick.openScopes(  this)
        scope.bindScopeAnnotation(MainViewModelScope::class.java)
        interactor=scope.getInstance(MainInteractor::class.java)
    }

    val requestTestLiveData by lazy {
        interactor.testRequest().map { t -> t }.subInLiveData()
    }

    val requestTestObservableField by lazy {
        interactor.testRequest().subInObserverbleField()
    }

}