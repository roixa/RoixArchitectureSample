package com.roix.mvvm_archtecture_sample.buissness.simple

import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import javax.inject.Inject

/**
 * Created by roix on 06.01.2018.
 */
class SimpleInteractor: ISimpleInteractor {

    private val serverRepository: IServerRepository

    @Inject constructor(serverRepository: IServerRepository) {
        this.serverRepository = serverRepository
    }

}