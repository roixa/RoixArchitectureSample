package com.roix.mvvm_archtecture_sample.data.repositories.server

import com.roix.mvvm_archtecture_sample.data.repositories.common.InfoException
import com.roix.mvvm_archtecture_sample.data.repositories.common.Parseble

/**
 * Created by belyalov on 26.12.2017.
 */
class ServerToDataConverter {
    companion object {

        fun <T> convertToData(parseble: Parseble<T>?): T {
            if (parseble == null || !parseble.isValid()) throw InfoException("invalid " + parseble?.javaClass.toString())
            else return parseble.parse()
        }

        fun <T> convertToListData(parsebleList: List<Parseble<T>>?): List<T> {
            val ret = ArrayList<T>()
            if (parsebleList == null) throw InfoException("empty list ")
            for (parseble in parsebleList) {
                if (parseble.isValid()) {
                    ret.add(parseble.parse())
                }
            }
            return ret
        }

    }
}