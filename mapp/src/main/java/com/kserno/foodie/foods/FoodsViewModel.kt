package com.kserno.foodie.foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Food

/**
 *  Created by filipsollar on 2019-04-10
 */
class FoodsViewModel(
        api: Api
): ViewModel() {

    val actionNew = MutableLiveData<Action<Void>>()
    val data = MutableLiveData<List<Food>>()

    init {
        api.getFoods(api.getCurrentUser()!!.restaurantId, "")
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }

    fun newClicked() {
        actionNew.postValue(Action())
    }

}