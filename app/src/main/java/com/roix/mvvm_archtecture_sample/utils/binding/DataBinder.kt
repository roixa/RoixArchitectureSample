package com.roix.mvvm_archtecture_sample.utils.binding

import android.databinding.BindingAdapter
import android.databinding.BindingConversion
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView

/**
 * Created by roix on 05.12.2017.
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:srcVector")
fun setSrcVector(imageView: ImageView, @DrawableRes res: Int) {
    imageView.setImageResource(res)
}

@BindingAdapter("bind:srcCompat")
fun setSrcCompat(imageView: ImageView, @DrawableRes res: Int) {
    imageView.setImageResource(res)
}

@BindingAdapter("bind:tint")
fun setTint(view: ImageView, @ColorRes colorRes: Int) {
    view.setColorFilter(ContextCompat.getColor(view.context, colorRes))
}

@BindingAdapter("bind:tintColor")
fun setTintColor(view: ImageView, color: Int) {
    view.setColorFilter(color)
}

@BindingAdapter("bind:colorInt")
fun setColorInt(view: ImageView, color: Int) {
    view.setColorFilter(color)
}
