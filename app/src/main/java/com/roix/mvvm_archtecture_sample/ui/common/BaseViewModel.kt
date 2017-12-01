package com.roix.mvvm_archtecture_sample.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by belyalov on 01.12.2017.
 */
open class BaseViewModel : ViewModel() {

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()


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