package com.roix.mvvm_archtecture_sample.utils.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by roix on 05.12.2017.
 */

public class DataBinder {
    @BindingConversion
    public static int convertBooleanToVisibility(final boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
    @BindingAdapter("bind:srcVector")
    public static void setSrcVector(@NonNull final ImageView imageView, @DrawableRes final int res) {
        imageView.setImageResource(res);
    }

    @BindingAdapter("bind:srcCompat")
    public static void setSrcCompat(@NonNull final ImageView imageView, @DrawableRes final int res) {
        imageView.setImageResource(res);
    }
    @BindingAdapter("bind:tint")
    public static void setTint(final ImageView view, @ColorRes final int colorRes) {
        view.setColorFilter(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @BindingAdapter("bind:tintColor")
    public static void setTintColor(final ImageView view, final int color) {
        view.setColorFilter(color);
    }

    @BindingAdapter("bind:colorInt")
    public static void setColorInt(final ImageView view, final int color) {
        view.setColorFilter(color);
    }

}
