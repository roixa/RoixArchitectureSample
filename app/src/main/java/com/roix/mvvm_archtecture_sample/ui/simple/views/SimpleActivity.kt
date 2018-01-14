package com.roix.mvvm_archtecture_sample.ui.simple.views

import android.support.v7.widget.Toolbar
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.databinding.ActivitySimpleBinding
import com.roix.mvvm_archtecture_sample.ui.common.activities.BaseSingleFragmentActivity
import com.roix.mvvm_archtecture_sample.ui.common.view.ToolbarType
import com.roix.mvvm_archtecture_sample.ui.main.views.MainFragment
import com.roix.mvvm_archtecture_sample.ui.simple.viewmodels.SimpleViewModel

class SimpleActivity : BaseSingleFragmentActivity<SimpleViewModel, ActivitySimpleBinding>() {

    override fun getFragmentContainerId(): Int = R.id.container


    override fun getLayoutId(): Int = R.layout.activity_simple


    override fun getToolbar(): Toolbar? = binding.toolbar.tb


    override fun configureToolbar(defaultToolbarType: ToolbarType.Builder): ToolbarType.Builder {
        return defaultToolbarType.setTitle(" roix architecture")
    }

    override fun setupUi() {
        super.setupUi()
        setFragment(MainFragment::class.java)
    }



}
