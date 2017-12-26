package com.roix.mvvm_archtecture_sample.ui.common.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.ui.common.viewmodels.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.reflect.ParameterizedType

/**
 * Created by belyalov on 01.12.2017.
 */
abstract class BaseLifecycleActivity<vm : BaseViewModel> : AppCompatActivity() {


    @IdRes
    abstract fun getLayoutId(): Int


    protected lateinit var viewModel: vm

    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        viewModel = ViewModelProviders.of(this).get(getViewModelJavaClass())
        setupUi()
    }

    protected open fun setupUi() {
        Log.d("roixa", "setupUi")

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.text_dialog_progress))
        progressDialog.setCancelable(false)

        viewModel.loadingLiveData.sub { b -> handleProgress(b) }
        viewModel.showMessageDialogLiveData.sub { s -> this.showMessageDialog(s) }
        viewModel.onBindView()

    }

    @CallSuper
    protected open fun handleProgress(isProgress: Boolean) {
        Log.d("roixa", "handleProgress " + isProgress)

        if (isProgress) {
            progressDialog.show()
        } else {
            progressDialog.hide()
        }
    }

    @CallSuper
    protected open fun showMessageDialog(message: String) {
        Log.d("roixa", "showMessageDialog " + message)

    }

    protected fun <T> LiveData<T>.sub(func: (T) -> Unit) {
        observe(this@BaseLifecycleActivity, Observer { T -> if (T != null) func.invoke(T) })
    }

    protected fun <T> Observable<T>.sub(func: (T) -> Unit) {
        viewModel.subInLiveDataFun(this).sub(func)
    }

    protected fun <T> Single<T>.sub(func: (T) -> Unit) {
        viewModel.subInLiveDataFun(this.toObservable()).sub(func)
    }

    private fun getViewModelJavaClass(): Class<vm> {
        return (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<vm>
    }

}