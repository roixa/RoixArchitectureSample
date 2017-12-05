package com.roix.mvvm_archtecture_sample.ui.main.views

import android.support.v7.widget.Toolbar
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.databinding.ActivityMainBinding
import com.roix.mvvm_archtecture_sample.ui.common.activities.BaseToolbarActivity
import com.roix.mvvm_archtecture_sample.ui.main.viewmodels.MainViewModel


class MainActivity : BaseToolbarActivity<MainViewModel, ActivityMainBinding>() {



    override fun getToolbar(): Toolbar? = binding.toolbar.tb

    override fun getLayoutId(): Int = R.layout.activity_main


}
