package com.roix.mvvm_archtecture_sample.ui.common.activities

import android.databinding.ViewDataBinding
import android.support.annotation.CallSuper
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.roix.mvvm_archtecture_sample.BR
import com.roix.mvvm_archtecture_sample.R
import com.roix.mvvm_archtecture_sample.databinding.MenuItemBinding
import com.roix.mvvm_archtecture_sample.ui.common.BaseDatabindingActivity
import com.roix.mvvm_archtecture_sample.ui.common.BaseViewModel
import com.roix.mvvm_archtecture_sample.ui.common.view.ToolbarType
import kotlinx.android.synthetic.main.toolbar.view.*

/**
 * Created by roix on 05.12.2017.
 */
abstract class BaseToolbarActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding> : BaseDatabindingActivity<ViewModel, DataBinding>() {

    abstract fun getToolbar(): Toolbar?

    override fun setupUi() {
        super.setupUi()
        setupToolbar(buildToolbar(ToolbarType.Builder(this).default()).build())
    }

    protected fun buildToolbar(defaultToolbarType: ToolbarType.Builder): ToolbarType.Builder {
        return defaultToolbarType
    }

    @CallSuper
    fun setupToolbar(toolbarType: ToolbarType) {
        //setSupportActionBar(getToolbar())
        binding.setVariable(BR.toolbarType, toolbarType)
        val toolbar=getToolbar()
        if (toolbar != null) {
            toolbar.navigation_tb
                    .setOnClickListener({
                        if (R.drawable.ic_navigation_menu === toolbarType.icon) {
                            openNavigationView()
                        } else if (R.drawable.ic_back === toolbarType.icon) {
                            goBack()
                        }
                    })
            clearToolbarItems()
        }
    }

    /**
     * Adds menu item to toolbar.
     *
     * @param drawableIcon    item icon
     * @param onClickListener action on click
     */
    @CallSuper
    fun addToolbarItem(@DrawableRes drawableIcon: Int, onClickListener: View.OnClickListener) {
        val toolbar=getToolbar()
        if (toolbar != null) {
            val view = LayoutInflater.from(this).inflate(R.layout.menu_item, getToolbar(), false)
            view.setOnClickListener(onClickListener)
            val itemContainer = toolbar.ll_items as LinearLayout
            itemContainer.addView(view)
            val menuItemBinding = MenuItemBinding.bind(view)
            menuItemBinding.icon = ContextCompat.getDrawable(this, drawableIcon)
        }
    }

    @CallSuper
    fun addToolbarItem(view: View) {
        val toolbar=getToolbar()

        if (toolbar != null) {
            val itemContainer = toolbar.ll_items as LinearLayout
            itemContainer.addView(view)
        }
    }

    fun clearToolbarItems() {
        val toolbar=getToolbar()

        if (toolbar != null) {
            val itemContainer = toolbar.ll_items as LinearLayout
            itemContainer.removeAllViews()
        }
    }

    /**
     * Finishes activity.
     */
    protected fun goBack() {
        supportFinishAfterTransition()
    }

    /**
     * Open navigation view when click on hamburger
     */
    protected fun openNavigationView() {
        // Need to override in child
    }

}