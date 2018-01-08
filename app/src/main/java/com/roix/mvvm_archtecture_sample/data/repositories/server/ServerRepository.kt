package com.roix.mvvm_archtecture_sample.data.repositories.server

import android.content.Context
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.toothpick.common.ApplicationScope
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by roix on 30.11.2017.
 */

@ApplicationScope
class ServerRepository: IServerRepository {

    private val BOARD = "b"

    private lateinit var serverApi: ServerApi
    //TODO maybe more separatize
    @Inject
    fun ServerRepository(context: Context) {
        val client = ServerApi.ServerFactory.createOkHttpClient(context)
        serverApi = ServerApi.ServerFactory.createInstance(client).create(ServerApi::class.java)
    }

    override fun getTestRequest(): Single<String> {
        return Single.create { s ->
            Thread.sleep(2000)
            s.onSuccess("Hello")
        }
    }

    override fun getThreads(page: Int): Single<List<ThreadItem>> = serverApi.getThreads(BOARD, page)
            .map { t -> ServerToDataConverter.convertToListData(t.threads) }


}