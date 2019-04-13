package com.kserno.foodie.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import com.kserno.foodie.PersistentLayer

/**
 *  Created by filipsollar on 2019-04-03
 */
class LoginViewModel(
        private val api: Api
): ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val actionNext = MutableLiveData<Action<Void>>()
    val actionError = MutableLiveData<Action<Void>>()

    fun login() {

        api.login(username.value ?: "", password.value ?: "")
                .applySchedulers()
                .subscribe({
                    actionNext.postValue(Action())
                }, {
                    actionError.postValue(Action())
                })
    }



}