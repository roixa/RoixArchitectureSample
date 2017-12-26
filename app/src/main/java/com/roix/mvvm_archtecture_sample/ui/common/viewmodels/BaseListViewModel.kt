package com.roix.mvvm_archtecture_sample.ui.common.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.roix.mvvm_archtecture_sample.buissness.common.BaseListInteractor

/**
 * Created by roix on 26.12.2017.
 */

abstract class BaseListViewModel<Item> : BaseViewModel() {

    private val mList: ObservableList<Item> = ObservableArrayList<Item>()

    protected abstract fun getInteractor(): BaseListInteractor<Item>


}