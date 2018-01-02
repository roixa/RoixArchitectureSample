package com.roix.mvvm_archtecture_sample.data.repositories.server

import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
interface IServerRepository {
    fun getTestRequest(): Single<String>

    fun getThreads(page: Int): Single<List<ThreadItem>>
}