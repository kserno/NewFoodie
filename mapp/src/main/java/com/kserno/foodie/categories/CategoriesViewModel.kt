package com.kserno.foodie.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.model.FoodCategory

/**
 *  Created by filipsollar on 2019-03-28
 */
class CategoriesViewModel(
        private val api: Api
) : ViewModel(){


    val data = MutableLiveData<List<FoodCategory>>()

    init {
        api.getFoodCategories()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }




}