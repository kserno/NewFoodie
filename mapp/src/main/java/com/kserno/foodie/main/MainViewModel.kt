package com.kserno.foodie.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import com.kserno.foodie.PersistentLayer

/**
 *  Created by filipsollar on 2019-04-10
 */
class MainViewModel(
        private val persistentLayer: PersistentLayer
): ViewModel() {

    val welcomeMessage = MutableLiveData<String>().apply { value = "Welcome, ${persistentLayer.currentUser?.name}!" }

    val actionFoods = MutableLiveData<Action<Void>>()
    val actionAccounts = MutableLiveData<Action<Void>>()
    val actionTables = MutableLiveData<Action<Void>>()
    val actionOrders = MutableLiveData<Action<Void>>()

    fun foodsClicked() {
        actionFoods.postValue(Action())
    }

    fun accountsClicked() {
        actionAccounts.postValue(Action())
    }

    fun tablesClicked() {
        actionTables.postValue(Action())
    }

    fun ordersClicked() {
        actionOrders.postValue(Action())
    }

}