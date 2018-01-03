package com.roix.mvvm_archtecture_sample.ui.common.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.roix.mvvm_archtecture_sample.buissness.common.IBaseListInteractor
import io.reactivex.Single

/**
 * Created by roix on 26.12.2017.
 */

abstract class BaseListViewModel<Item> : BaseDatabindingViewModel() {

    val list: ObservableList<Item> = ObservableArrayList<Item>()

    private var mNextPage: Int = getMinPage()

    protected abstract fun getInteractor(): IBaseListInteractor<Item>

    override fun onBindFirstView() {
        super.onBindFirstView()
        loadNextItems().sub { l ->
            list.addAll(l)
        }
    }

    protected fun loadNextItems(): Single<List<Item>> {
        if (mNextPage > getMaxPage()) {
            return Single.just(emptyList())
        } else {
            return getInteractor()
                    .loadItems(mNextPage)
                    .map { t ->
                        val item = t.last()
                        mNextPage = getNextPage(item)
                        return@map t
                    }
        }
    }

    //override if needs
    protected open fun getNextPage(lastItem: Item): Int = mNextPage + 1

    //override if needs
    protected open fun getMinPage(): Int = 1

    //override if needs
    protected open fun getMaxPage(): Int = Int.MAX_VALUE

}