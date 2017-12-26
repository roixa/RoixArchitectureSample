package com.roix.mvvm_archtecture_sample.buissness.common

import io.reactivex.Single

/**
 * Created by roix on 26.12.2017.
 */
abstract class BaseListInteractor<Item> {

    private var mNextPage: Int = 1

    protected val maxPage = Int.MAX_VALUE

    abstract fun loadItems(page: Int): Single<List<Item>>

    open fun loadNextItems(): Single<List<Item>> = loadItems(mNextPage)
            .map { t ->
                val item = t.last()
                mNextPage = getNextPage(item)
                return@map t
            }

    //**
    // override this, if you want to, for example, last item id pagination
    // */
    open fun getNextPage(last: Item): Int = mNextPage + 1

}