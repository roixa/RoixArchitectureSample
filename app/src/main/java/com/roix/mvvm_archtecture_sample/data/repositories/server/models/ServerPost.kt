package com.roix.mvvm_archtecture_sample.data.repositories.server.models

import com.roix.mvvm_archtecture_sample.data.Parseble
import com.roix.mvvm_archtecture_sample.data.models.ThreadHeader
import com.roix.mvvm_archtecture_sample.data.repositories.server.ServerToDataConverter

/**
 * Created by belyalov on 26.12.2017.
 */
class ServerPost : Parseble<ThreadHeader> {

    var comment : String? = null
    var files : List<ServerFile>? = null

    override fun isValid(): Boolean = comment != null

    override fun parse(): ThreadHeader {
        return ThreadHeader(comment!!,ServerToDataConverter.convertToListData(files!!))
    }

}