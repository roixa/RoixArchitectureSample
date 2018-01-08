package com.roix.mvvm_archtecture_sample.ui.common.viewmodels

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.ui.common.loading.ILoadingObserver
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module

/**
 * Created by belyalov on 01.12.2017.
 */
abstract class BaseViewModel : ViewModel() {


    private var viewsCount = 0
    protected val subscription: CompositeDisposable = CompositeDisposable()

    private lateinit var viewModelScope: Scope

    protected abstract fun getModule(): Module

    protected abstract fun getViewModelScope(): Class<BaseViewModel>

    protected abstract fun doInject(scope: Scope)

    @CallSuper
    open fun onBindView(application: CommonApplication) {
        proceedInject(application)
        if (viewsCount == 0) {
            onBindFirstView()
        }
        viewsCount++
    }

    protected open fun proceedInject(application: CommonApplication) {
        viewModelScope = Toothpick.openScope(getViewModelScope())
        viewModelScope.installModules(getModule())
        doInject(viewModelScope)
        //interactor = viewModelScope.getInstance(getInteractorlJavaClass())
        val applicationScope = Toothpick.openScope(application)
        //interactor.doInject(applicationScope)
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
        Toothpick.closeScope(viewModelScope)
    }

    open fun <T> Observable<T>.withDefaultShedulers(): Observable<T> {
        return subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
    }

    open fun Completable.withDefaultShedulers(): Completable {
        return subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
    }


    fun <T> Observable<T>.withLoadingHandle(loading: ILoadingObserver): Observable<T> {
        return doOnSubscribe({
            loading.onStartLoad()
        }).doAfterTerminate({
            loading.onEndLoad()
        })
    }

    fun Completable.withLoadingHandle(loading: ILoadingObserver): Completable {
        return doOnSubscribe({
            loading.onStartLoad()
        }).doAfterTerminate({
            loading.onEndLoad()
        })
    }


    abstract fun <T> Observable<T>.withDefaultLoadingHandle(): Observable<T>

    abstract fun <T> Observable<T>.defaultErrorHandle(error: Throwable)


    fun <T> Observable<T>.sub(function: (T) -> Unit) {
        subscription.add(
                withDefaultLoadingHandle().
                        withDefaultShedulers().
                        subscribe({ T ->
                            function.invoke(T)
                        }, { t -> defaultErrorHandle(t) })
        )
    }

    fun <T> Single<T>.sub(function: (T) -> Unit) = this.toObservable().sub { T -> function.invoke(T) }


}