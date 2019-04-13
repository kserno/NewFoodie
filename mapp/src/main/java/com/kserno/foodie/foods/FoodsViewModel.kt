package com.kserno.foodie.foods

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodCategory

/**
 *  Created by filipsollar on 2019-04-10
 */
class FoodsViewModel(
        api: Api,
        category: FoodCategory
): ViewModel() {

    val actionNew = MutableLiveData<Action<Void>>()
    val data = MutableLiveData<List<Food>>()
    val textToolbar = MutableLiveData<String>().apply { value = category.name }


    init {
        api.getFoods(api.getCurrentUser()!!.restaurantId, category.id
        )
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