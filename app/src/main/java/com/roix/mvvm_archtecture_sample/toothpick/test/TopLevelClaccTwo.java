package com.roix.mvvm_archtecture_sample.toothpick.test;

import com.roix.mvvm_archtecture_sample.toothpick.main.MainViewModelScope;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

/**
 * Created by roix on 08.01.2018.
 */
@MainViewModelScope
public class TopLevelClaccTwo {
    @Inject
    InnerLevelClassTwo obj;

    public TopLevelClaccTwo(){
        Scope s=Toothpick.openScope(this);
        s.bindScopeAnnotation(MainViewModelScope.class);
        Toothpick.inject(this,s);
        obj.getParam();
    }

}
