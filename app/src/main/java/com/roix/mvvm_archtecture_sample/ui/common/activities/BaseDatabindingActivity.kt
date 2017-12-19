package com.roix.mvvm_archtecture_sample.ui.common.activities

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import com.android.databinding.library.baseAdapters.BR
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseViewModel

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
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(BR.viewmodel,viewModel)
    }
}