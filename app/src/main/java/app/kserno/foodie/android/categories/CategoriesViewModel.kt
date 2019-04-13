package app.kserno.foodie.android.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.model.FoodCategory
import app.kserno.foodie.common.model.FoodOrder
import app.kserno.foodie.common.model.FoodOrderModel

/**
 *  Created by filipsollar on 2019-03-28
 */
class CategoriesViewModel(
        private val api: Api
) : ViewModel(){


    val data = MutableLiveData<List<FoodCategory>>()
    val order = MutableLiveData<List<FoodOrderModel>>()
    val actionOrder = MutableLiveData<Action<Void>>()
    val textPrice = MutableLiveData<String>()

    init {
        api.getFoodCategories()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })

        api.newOrder.
                applySchedulers()
                .subscribe({
                    order.postValue(it)
                    val sum = it.sumByDouble { it.count * it.food.price }
                    textPrice.postValue("Total: $sum")
                }, {
                    it.printStackTrace()
                })


    }

    fun orderClicked() {
        actionOrder.postValue(Action())
    }






}