package com.roix.mvvm_archtecture_sample.ui.main.views

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.databinding.ActivityMainBinding
import com.roix.mvvm_archtecture_sample.databinding.ItemThreadBinding
import com.roix.mvvm_archtecture_sample.ui.common.activities.BaseListActivity
import com.roix.mvvm_archtecture_sample.ui.common.view.ToolbarType
import com.roix.mvvm_archtecture_sample.ui.main.viewmodels.MainViewModel


class MainActivity : BaseListActivity<MainViewModel, ActivityMainBinding,ItemThreadBinding, ThreadItem>() {


    override fun getRecyclerView(): RecyclerView = binding.rv

    override fun getSwipeToRefreshLayout(): SwipeRefreshLayout? = binding.srl

    override fun getToolbar(): Toolbar? = binding.toolbar.tb

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getItemLayoutId(): Int = R.layout.item_thread

    override fun configureToolbar(defaultToolbarType: ToolbarType.Builder): ToolbarType.Builder {
        return defaultToolbarType.setTitle(" roix architecture").setNavigationIcon()
    }

}
