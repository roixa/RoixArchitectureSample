package com.roix.mvvm_archtecture_sample.data.repositories.server.models

import com.roix.mvvm_archtecture_sample.data.models.ThreadItem
import com.roix.mvvm_archtecture_sample.data.repositories.common.Parseble
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerToDataConverter

/**
 * Created by belyalov on 26.12.2017.
 */
class ServerThreadListResponse : Parseble<List<ThreadItem>> {

    var threads: List<ServerThread>? = null

    override fun isValid(): Boolean = threads != null

    override fun parse(): List<ThreadItem> = ServerToDataConverter.convertToListData(threads!!)


}