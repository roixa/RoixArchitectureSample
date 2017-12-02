package com.roix.mvvm_archtecture_sample.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import android.util.Log
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.dagger.common.DaggerAppComponent
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by belyalov on 01.12.2017.
 */
abstract class BaseViewModel : ViewModel() {

    private var viewsCount = 0

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    val showMessageDialogLiveData: MutableLiveData<String> = MutableLiveData()

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

    protected open fun onBindFirstView() {}


    fun <T> toLiveData(observable: Observable<T>): LiveData<T> {
        val ret = MutableLiveData<T>()

        observable
                .withDefaultShedulers()
                .withLoadingHandle()

                .subscribe({ t ->
                    run {
                        ret.value=t
                        Log.d("roixa", "in toLiveData observeble subscribe " + t)

                        Log.d("roixa", "Back on the original thread:  " + Thread.currentThread().name)
                    }
                }, { t -> errorLiveData.value = t })

        return ret
    }

    fun <T> Observable<T>.withLoadingHandle(): Observable<T> {
        return doOnSubscribe({ loadingLiveData.postValue(true) }).
                    doAfterTerminate({ loadingLiveData.postValue( false) })


    }

    fun <T> Observable<T>.withDefaultShedulers(): Observable<T> {
        return subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Observable<T>.sub(function: (T) -> Unit) {
        withLoadingHandle().
        withDefaultShedulers().
        subscribe({ T ->
            function.invoke(T)
        }, { t -> errorLiveData.postValue( t) })
    }

    fun <T> Single<T>.sub(function: (T) -> Unit) = this.toObservable().sub { T -> function.invoke(T) }


}