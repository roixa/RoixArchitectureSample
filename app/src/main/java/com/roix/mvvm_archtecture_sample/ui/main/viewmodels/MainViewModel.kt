package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import android.util.Log
import com.roix.mvvm_archtecture_sample.buissness.common.BaseListInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.data.models.ThreadHeader
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseListViewModel
import com.roix.mvvm_archtecture_sample.utils.extensions.setValueNoHistory
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseListViewModel<ThreadHeader>() {
    @Inject
    protected lateinit var mainInteractor: MainInteractor

    override fun getInteractor(): BaseListInteractor<ThreadHeader> = mainInteractor



    override fun doInject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onBindFirstView() {
        super.onBindFirstView()

        mainInteractor.testRequest().sub { s ->
            showMessageDialogLiveData.setValueNoHistory(s)
            requestTestObservableField.set(s)
        }

        mainInteractor.getThreads(1).sub { list ->
            Log.d("roix",list.size.toString())
        }
    }


    val requestTestLiveData by lazy {
        mainInteractor.testRequest().map { t -> t }.subInLiveData()
    }

    val requestTestObservableField by lazy {
        mainInteractor.testRequest().subInObserverbleField()
    }

}