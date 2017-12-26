package com.roix.mvvm_archtecture_sample.data

/**
 * Created by belyalov on 26.12.2017.
 */
interface Parseble<out T> {
    fun isValid(): Boolean
    fun parse(): T
}