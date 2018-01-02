package com.roix.mvvm_archtecture_sample.data.repositories.server.models

import com.roix.mvvm_archtecture_sample.data.Parseble
import com.roix.mvvm_archtecture_sample.data.models.ThreadItem

/**
 * Created by belyalov on 26.12.2017.
 */
class ServerThread : Parseble<ThreadItem> {

    var posts: List<ServerPost>? = null

    override fun isValid(): Boolean {
        if(posts != null){
            if (posts!!.size != 0){
                return true
            }
        }
        return false
    }

    override fun parse(): ThreadItem = posts!![0].parse()


}