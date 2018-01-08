package com.roix.mvvm_archtecture_sample.toothpick.test

import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by roix on 08.01.2018.
 */
open class TopLevelCkass {
    @Inject
    lateinit var obj : InnerLevelClassOne

    init {
        val scope=Toothpick.openScope(this)
        Toothpick.inject(this,scope)
        obj.getParam()
    }
}