package com.roix.mvvm_archtecture_sample.data.repositories.server

import com.roix.mvvm_archtecture_sample.data.models.ThreadHeader
import io.reactivex.Single

/**
 * Created by roix on 30.11.2017.
 */
class ServerRepository(val serverApi: ServerApi) : IServerRepository {

    private val BOARD = "b"

    override fun getTestRequest(): Single<String> {
        return Single.create { s ->
            Thread.sleep(2000)
            s.onSuccess("Hello")
        }
    }

    override fun getThreads(page: Int): Single<List<ThreadHeader>> = serverApi.getThreads(BOARD, page)
            .map { t -> ServerToDataConverter.convertToListData(t.threads) }


}