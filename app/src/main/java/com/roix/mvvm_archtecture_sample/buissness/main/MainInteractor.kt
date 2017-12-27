package com.roix.mvvm_archtecture_sample.buissness.main

import com.roix.mvvm_archtecture_sample.buissness.common.BaseListInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadHeader
import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by roix on 29.11.2017.
 */
class MainInteractor : IMainInteractor, BaseListInteractor<ThreadHeader> {


    private val serverRepository: IServerRepository

    @Inject constructor(serverRepository: IServerRepository) {
        this.serverRepository = serverRepository
    }

    override fun loadItems(page: Int): Single<List<ThreadHeader>> = serverRepository.getThreads(page)

    override fun testRequest(): Single<String> = serverRepository.getTestRequest()

    override fun getThreads(page: Int): Single<List<ThreadHeader>> = serverRepository.getThreads(page)


}