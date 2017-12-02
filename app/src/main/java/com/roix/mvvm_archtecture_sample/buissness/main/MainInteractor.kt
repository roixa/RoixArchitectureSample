package com.roix.mvvm_archtecture_sample.buissness.main

import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by roix on 29.11.2017.
 */
class MainInteractor : IMainInteractor {

    private val serverRepository: IServerRepository;

    @Inject constructor(serverRepository: IServerRepository) {
        this.serverRepository = serverRepository
    }

    override fun testRequest(): Single<String> = serverRepository.getTestRequest()

}