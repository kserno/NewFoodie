package com.kserno.foodie.addtable

import androidx.lifecycle.MutableLiveData
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers

/**
 *  Created by filipsollar on 2019-04-11
 */
class AddTableViewModel(
        private val api: Api
) {

    val name = MutableLiveData<String>()
    val beaconId = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val actionDone = MutableLiveData<Action<Void>>()

    fun addTableClicked() {
        api.addTable(name.value ?: "", beaconId.value ?: "")
                .applySchedulers()
                .subscribe({
                    isLoading.postValue(false)
                    actionDone.postValue(Action())
                }, {
                    it.printStackTrace()
                })
    }

}