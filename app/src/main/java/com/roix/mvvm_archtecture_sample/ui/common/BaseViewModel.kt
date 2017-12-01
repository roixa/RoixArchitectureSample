package com.roix.mvvm_archtecture_sample.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.dagger.common.DaggerAppComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by belyalov on 01.12.2017.
 */
open abstract class BaseViewModel : ViewModel() {

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()

    abstract fun doInject(appComponent: AppComponent)

    //TODO maybe create more correct inject
    init {
        doInject(CommonApplication.appComponent)
    }

    fun <T> Observable<T>.toLiveData(): LiveData<T> {
        withLoadingHandle()
        withDefaultShedulers()
        val ret = MutableLiveData<T>()
        subscribe({ t -> ret.postValue(t) }, { t -> errorLiveData.postValue(t) })
        return ret
    }

    fun <T> Observable<T>.withLoadingHandle(): Observable<T> {
        doOnSubscribe({ loadingLiveData.postValue(true) })
        doAfterTerminate({ loadingLiveData.postValue(false) })
        return this
    }

    fun <T> Observable<T>.withDefaultShedulers(): Observable<T> {
        observeOn(Schedulers.io())
        subscribeOn(AndroidSchedulers.mainThread())
        return this
    }

    fun <T> Observable<T>.sub(viewModel: BaseViewModel, function: () -> T) {
        withLoadingHandle()
        withDefaultShedulers()
        subscribe({function},{t -> errorLiveData.postValue(t) })
    }

}