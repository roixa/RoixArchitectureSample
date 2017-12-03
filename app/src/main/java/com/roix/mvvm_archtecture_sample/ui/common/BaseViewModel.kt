package com.roix.mvvm_archtecture_sample.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.annotation.CallSuper
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by belyalov on 01.12.2017.
 */
abstract class BaseViewModel : ViewModel() {


    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()


    private var viewsCount = 0
    private var loadingCount = 0
    private val subscription: CompositeDisposable = CompositeDisposable()

    abstract fun doInject(appComponent: AppComponent)

    //TODO maybe create more correct inject
    init {
        doInject(CommonApplication.appComponent)
    }

    @CallSuper
    fun onBindView() {
        if (viewsCount == 0) {
            onBindFirstView()
        }
        viewsCount++
    }

    @CallSuper
    protected open fun onBindFirstView() {
        onBindFirstView(subscription)
    }

    protected open fun onBindFirstView(subscription: CompositeDisposable) {}

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun <T> Observable<T>.withLoadingHandle(): Observable<T> {
        return doOnSubscribe({
            loadingCount++
            loadingLiveData.postValue(true)
        }).doAfterTerminate({
            loadingCount--
            if (loadingCount <= 0) {
                loadingLiveData.postValue(false)
            }
        })
    }

    fun <T> Observable<T>.withDefaultShedulers(): Observable<T> {
        return subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> toLiveDataFun(observable: Observable<T>): LiveData<T> {
        val ret = MutableLiveData<T>()
        subscription.add(
                observable
                        .withDefaultShedulers()
                        .withLoadingHandle()
                        .subscribe({ t ->
                            run {
                                ret.value = t
                            }
                        }, { t -> errorLiveData.value = t })
        )

        return ret
    }

    fun <T> Observable<T>.toObserverbleField(): ObservableField<T> {
        val ret = ObservableField<T>()
        sub { t ->
            ret.set(t)
        }
        return ret
    }

    fun <T> Single<T>.toObserverbleField(): ObservableField<T> = toObservable().toObserverbleField()

    fun <T> Observable<T>.toLiveData(): LiveData<T> = this@BaseViewModel.toLiveDataFun(this)

    fun <T> Single<T>.toLiveData(): LiveData<T> = this@BaseViewModel.toLiveDataFun(this.toObservable())

    fun <T> Observable<T>.sub(function: (T) -> Unit) {
        subscription.add(
                withLoadingHandle().
                        withDefaultShedulers().
                        subscribe({ T ->
                            function.invoke(T)
                        }, { t -> errorLiveData.postValue(t) })
        )
    }

    fun <T> Single<T>.sub(function: (T) -> Unit) = this.toObservable().sub { T -> function.invoke(T) }


}