package com.kserno.foodie.addaccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers

/**
 *  Created by filipsollar on 2019-04-10
 */
class AddAccountViewModel(
        private val api: Api
): ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()

    val actionCreated = MutableLiveData<Action<Void>>()

    fun createClicked() {
        loading.postValue(true)
        api.addAccount(
                name.value ?: "",
                username.value ?: "",
                password.value ?: ""
        ).applySchedulers()
                .subscribe({
                    actionCreated.postValue(Action())
                }, {
                    it.printStackTrace()
                })
    }




}