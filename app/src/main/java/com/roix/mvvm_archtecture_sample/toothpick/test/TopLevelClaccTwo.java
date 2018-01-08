package com.roix.mvvm_archtecture_sample.toothpick.test;

import android.app.Application;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

/**
 * Created by roix on 08.01.2018.
 */
public class TopLevelClaccTwo {
    @Inject
    InnerLevelClassTwo obj;

    public TopLevelClaccTwo(Application application){
        Scope s=Toothpick.openScopes(this,application);
        Toothpick.inject(this,s);
        obj.getParam();
    }

}
