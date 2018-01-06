package com.roix.mvvm_archtecture_sample.ui.simple.views

import android.support.v7.widget.Toolbar
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.databinding.ActivitySimpleBinding
import com.roix.mvvm_archtecture_sample.ui.common.activities.BaseToolbarActivity
import com.roix.mvvm_archtecture_sample.ui.simple.viewmodels.SimpleViewModel

class SimpleActivity : BaseToolbarActivity<SimpleViewModel, ActivitySimpleBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_simple


    override fun getToolbar(): Toolbar? = binding.toolbar.tb

}
