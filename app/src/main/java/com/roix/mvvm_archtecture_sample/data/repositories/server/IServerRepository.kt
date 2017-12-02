package com.roix.mvvm_archtecture_sample.data.repositories.server

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
interface IServerRepository {
    fun getTestRequest(): Single<String>
}