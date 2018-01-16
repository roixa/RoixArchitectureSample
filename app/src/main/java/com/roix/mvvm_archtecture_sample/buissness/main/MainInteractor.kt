package com.roix.mvvm_archtecture_sample.buissness.main

import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by roix on 29.11.2017.
 */
class MainInteractor : IMainInteractor, IBaseListInteractor<ThreadItem> {

    @Inject constructor()

    @Inject lateinit var serverRepository: ServerRepository

    override fun loadItems(page: Int): Single<List<ThreadItem>> = serverRepository.getThreads(page)

    override fun testRequest(): Single<String> = serverRepository.getTestRequest()

}