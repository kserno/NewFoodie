package com.kserno.foodie.accounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.User

/**
 *  Created by filipsollar on 2019-04-10
 */
class AccountsViewModel(
        api: Api
): ViewModel() {

    val users = MutableLiveData<List<User>>()
    val actionNew = MutableLiveData<Action<Void>>()


    init {
        api.getUsers(api.getCurrentUser()!!.restaurantId)
                .applySchedulers()
                .subscribe({
                    users.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }

    fun newClicked() {
        actionNew.postValue(Action())
    }



}