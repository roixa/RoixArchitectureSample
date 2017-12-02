package com.roix.mvvm_archtecture_sample.ui.main.viewmodels

import android.util.Log
import com.roix.mvvm_archtecture_sample.application.CommonApplication
import com.roix.mvvm_archtecture_sample.buissness.main.IMainInteractor
import com.roix.mvvm_archtecture_sample.buissness.main.MainInteractor
import com.roix.mvvm_archtecture_sample.dagger.common.AppComponent
import com.roix.mvvm_archtecture_sample.ui.common.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */
class MainViewModel : BaseViewModel(), IMainViewModel {

    @Inject
    protected lateinit var mainInteractor: IMainInteractor

    val requestTest by lazy {
        toLiveData(mainInteractor.testRequest().toObservable())
    }

    override fun doInject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onBindFirstView() {
        super.onBindFirstView()
        Log.d("roixa", "onBindFirstView")

        mainInteractor.testRequest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe { s ->
            Log.d("roixa", "testRequest lassic" + s)

        }

        mainInteractor.testRequest().sub { s ->
            run {
                showMessageDialogLiveData.postValue(s)
                Log.d("roixa", "testRequest" + s)

            }
        }
    }

    override fun getTestRequest(): Single<String> = mainInteractor.testRequest()


}