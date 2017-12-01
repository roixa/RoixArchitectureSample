package com.roix.mvvm_archtecture_sample.ui.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by belyalov on 01.12.2017.
 */
abstract open class BaseActivity<vm : ViewModel> : AppCompatActivity() {

    abstract fun getToolbar(): Toolbar?

    @IdRes
    abstract fun getLayoutId(): Int

    protected lateinit var viewModle: vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setSupportActionBar(getToolbar())
        viewModle = ViewModelProviders.of(this).get(viewModle.javaClass)
        setupUi()
    }

    protected fun setupUi(){

    }

}