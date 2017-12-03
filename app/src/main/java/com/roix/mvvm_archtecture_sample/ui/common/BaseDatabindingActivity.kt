package com.roix.mvvm_archtecture_sample.ui.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.util.Log
import com.android.databinding.library.baseAdapters.BR

/**
 * Created by roix on 02.12.2017.
 */
abstract class BaseDatabindingActivity<vm : BaseViewModel, DataBinding : ViewDataBinding> : BaseLifecycleActivity<vm>() {

    protected lateinit var binding: DataBinding

    override fun setupUi() {
        super.setupUi()

        setupBinding()
    }

    @CallSuper
    protected open fun setupBinding() {
        Log.d("roix","setupBinding")
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(BR.viewmodel,viewModel)
        //TODO bad toolbar work
        setSupportActionBar(getToolbar())

    }
}