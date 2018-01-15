package com.roix.mvvm_archtecture_sample.ui.common.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import com.roix.mvvm_archtecture_sample.ui.common.loading.LoadingLiveData
import io.reactivex.*

/**
 * Created by roix on 03.01.2018.
 */
abstract class BaseLifecycleViewModel : BaseViewModel() {

    val loadingLiveData: LoadingLiveData = LoadingLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()

    override fun <T> Observable<T>.withDefaultLoadingHandle(): Observable<T> {
        return withLoadingHandle(loadingLiveData)
    }

    override fun <T> Observable<T>.defaultErrorHandle(error: Throwable) {
        errorLiveData.postValue(error)
    }

    fun <T> toLiveDataFun(observable: Observable<T>): LiveData<T> = LiveDataReactiveStreams.fromPublisher(observable.withDefaultLoadingHandle().withDefaultShedulers().toFlowable(BackpressureStrategy.BUFFER))

    fun <T>MutableLiveData<T>.setValueNoHistory(t:T){
        value=(t)
        value=(null)
    }

    fun <T> Observable<T>.toLiveData(): LiveData<T> = this@BaseLifecycleViewModel.toLiveDataFun(this)

    fun <T> Single<T>.toLiveData(): LiveData<T> = this@BaseLifecycleViewModel.toLiveDataFun(this.toObservable())

    fun <T> Flowable<T>.toLiveData(): LiveData<T> = this@BaseLifecycleViewModel.toLiveDataFun(this.toObservable())

    fun <T> Completable.toLiveData(): LiveData<T> = this@BaseLifecycleViewModel.toLiveDataFun(this.toObservable())

}