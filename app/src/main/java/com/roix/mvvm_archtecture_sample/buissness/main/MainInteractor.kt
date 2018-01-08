package com.roix.mvvm_archtecture_sample.buissness.main

import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.data.repositories.server.IServerRepository
import com.roix.mvvm_archtecture_sample.toothpick.main.MainViewModelScope
import io.reactivex.Single
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by roix on 29.11.2017.
 */
@MainViewModelScope
open class MainInteractor : IMainInteractor, IBaseListInteractor<ThreadItem> {

    @Inject
    fun MainInteractor(){}

    protected lateinit var serverRepository: IServerRepository

    override fun doInject(scope: Scope) {
        Toothpick.inject(this,scope)
        serverRepository =scope.getInstance(IServerRepository::class.java)
    }

    override fun loadItems(page: Int): Single<List<ThreadItem>> = serverRepository.getThreads(page)

    override fun testRequest(): Single<String> = serverRepository.getTestRequest()

    override fun getThreads(page: Int): Single<List<ThreadItem>> = serverRepository.getThreads(page)

}