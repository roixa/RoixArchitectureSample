package com.roix.mvvm_archtecture_sample.data.repositories.server

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
class ServerRepository:IServerRepository {
    override fun getTestRequest(): Single<String> {
        return Single.create { s->
            Thread.sleep(2000)
            s.onSuccess("Hello")
        }
    }
}