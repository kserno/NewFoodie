package com.kserno.foodie.table

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Order

/**
 *  Created by filipsollar on 2019-04-13
 */
class TableViewModel(
        private val wsService: WsService
): ViewModel() {

    val data = MutableLiveData<Order>()

    init {
        wsService.getObservable()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }


}