package com.roix.mvvm_archtecture_sample.ui.common.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.annotation.CallSuper
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.utils.livedata.LoadingLiveData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by belyalov on 01.12.2017.
 */
abstract class BaseViewModel : ViewModel() {


    val loadingLiveData: LoadingLiveData = LoadingLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()


    private var viewsCount = 0
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

    fun <T> Observable<T>.withDefaultLoadingHandle(): Observable<T> {
        return withLoadingLiveData(loadingLiveData)
    }

    fun <T> Observable<T>.withLoadingLiveData(loadingLiveData: LoadingLiveData): Observable<T> {
        return doOnSubscribe({
            loadingLiveData.onStartLoad()
        }).doAfterTerminate({
            loadingLiveData.onEndLoad()
        })
    }

    fun <T> Single<T>.withLoadingLiveData(loadingLiveData: LoadingLiveData): Single<T> {
        return doOnSubscribe({
            loadingLiveData.onStartLoad()
        }).doAfterTerminate({
            loadingLiveData.onEndLoad()
        })
    }


    fun <T> Observable<T>.withDefaultShedulers(): Observable<T> {
        return subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Observable<T>.sub(function: (T) -> Unit) {
        subscription.add(
                withDefaultLoadingHandle().
                        withDefaultShedulers().
                        subscribe({ T ->
                            function.invoke(T)
                        }, { t -> errorLiveData.postValue(t) })
        )
    }

    fun <T> subInLiveDataFun(observable: Observable<T>): LiveData<T> {
        val ret = MutableLiveData<T>()
        observable.sub { t ->
            ret.value=t
        }
        return ret
    }

    fun <T> Observable<T>.subInObserverbleField(): ObservableField<T> {
        val ret = ObservableField<T>()
        sub { t ->
            ret.set(t)
        }
        return ret
    }

    fun <T> Single<T>.subInObserverbleField(): ObservableField<T> = toObservable().subInObserverbleField()

    fun <T> Observable<T>.subInLiveData(): LiveData<T> = this@BaseViewModel.subInLiveDataFun(this)

    fun <T> Single<T>.subInLiveData(): LiveData<T> = this@BaseViewModel.subInLiveDataFun(this.toObservable())

    fun <T> Single<T>.sub(function: (T) -> Unit) = this.toObservable().sub { T -> function.invoke(T) }


}