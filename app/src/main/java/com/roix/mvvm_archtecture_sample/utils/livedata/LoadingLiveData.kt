package com.roix.mvvm_archtecture_sample.utils.livedata

import android.arch.lifecycle.LiveData

/**
 * Created by belyalov on 19.12.2017.
 */

class LoadingLiveData : LiveData<Boolean>() {

    private var loadingCount = 0

    fun onStartLoad() {
        loadingCount++
        postValue(true)
    }

    fun onEndLoad() {
        loadingCount--
        if (loadingCount <= 0) postValue(false)
    }
}