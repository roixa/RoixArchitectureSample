package com.roix.mvvm_archtecture_sample.toothpick.test;

import com.roix.mvvm_archtecture_sample.toothpick.main.MainViewModelScope;

import javax.inject.Inject;

/**
 * Created by roix on 08.01.2018.
 */
@MainViewModelScope
public class InnerLevelClassTwo {
    @Inject
    public InnerLevelClassTwo(){}

    public int getParam(){
        return 3;
    }
}
