package com.kserno.foodie.addtable

import androidx.lifecycle.MutableLiveData
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

    fun addTableClicked() {
        api.addTable(name.value ?: "", beaconId.value ?: "")
                .applySchedulers()
                .subscribe({
                    isLoading.postValue(false)
                }, {
                    it.printStackTrace()
                })
    }

}