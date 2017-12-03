package com.roix.mvvm_archtecture_sample.utils.extensions

import android.arch.lifecycle.MutableLiveData

/**
 * Created by roix on 02.12.2017.
 */

fun <T>MutableLiveData<T>.setValueNoHistory(t:T){
    value=(t)
    value=(null)
}

