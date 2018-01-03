package com.roix.mvvm_archtecture_sample.ui.common.activities

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.roix.mvvm_archtecture_sample.ui.common.adapters.BaseObservableAdapter
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseListViewModel

/**
 * Created by roix on 27.12.2017.
 */
abstract class BaseListActivity<ViewModel : BaseListViewModel<Item>, DataBinding : ViewDataBinding, ItemDataBinding : ViewDataBinding, Item>
    : BaseToolbarActivity<ViewModel, DataBinding>() {


    @LayoutRes
    protected abstract fun getItemLayoutId(): Int

    protected abstract fun getRecyclerView(): RecyclerView

    override fun setupUi() {
        super.setupUi()
        setupRecyclerView(getRecyclerView())
    }

    protected open fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BaseObservableAdapter<Item, ItemDataBinding>(viewModel.list, getItemLayoutId())
        }

    }
}