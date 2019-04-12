package com.kserno.foodie.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Table

/**
 *  Created by filipsollar on 2019-04-10
 */
class TablesViewModel(
        private val api: Api
): ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = true }
    val actionNew = MutableLiveData<Action<Void>>()
    val data = MutableLiveData<List<Table>>()

    init {
        api.getTables(api.getCurrentUser()!!.restaurantId)
                .applySchedulers()
                .subscribe({
                    isLoading.postValue(false)
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }

    fun newClicked() {
        actionNew.postValue(Action())
    }

}