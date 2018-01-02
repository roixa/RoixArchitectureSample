package com.roix.mvvm_archtecture_sample.data.repositories.server.models

import com.roix.mvvm_archtecture_sample.data.Parseble
import com.roix.mvvm_archtecture_sample.data.models.FileItem

/**
 * Created by belyalov on 26.12.2017.
 */
class ServerFile : Parseble<FileItem>{

    var path: String? = null
    var thumbnail: String? = null

    override fun isValid(): Boolean = path!=null

    override fun parse(): FileItem = FileItem(path!!,thumbnail)


}