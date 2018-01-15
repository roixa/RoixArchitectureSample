package com.roix.mvvm_archtecture_sample.buissness.simple

import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by roix on 06.01.2018.
 */
class SimpleInteractor: ISimpleInteractor {

    private val serverRepository: IServerRepository

    @Inject constructor(serverRepository: ServerRepository) {
        this.serverRepository = serverRepository
    }

    override fun testRequest(): Single<String> = serverRepository.getTestRequest()

}