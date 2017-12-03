package com.roix.mvvm_archtecture_sample.ui.main.views

import android.support.v7.widget.Toolbar
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.databinding.ActivityMainBinding
import com.roix.mvvm_archtecture_sample.ui.common.BaseDatabindingActivity
import com.roix.mvvm_archtecture_sample.ui.main.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.toolbar.view.*


class MainActivity : BaseDatabindingActivity<MainViewModel, ActivityMainBinding>() {



    override fun getToolbar(): Toolbar? = binding.tb.toolbar

    override fun getLayoutId(): Int = R.layout.activity_main


}
