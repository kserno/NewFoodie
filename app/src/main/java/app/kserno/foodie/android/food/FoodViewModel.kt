package app.kserno.foodie.android.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodCategory
import app.kserno.foodie.common.model.FoodOrderModel
import io.reactivex.Single

/**
 *  Created by filipsollar on 2019-03-28
 */
class FoodViewModel(
        private val api: Api,
        private val category: FoodCategory
): ViewModel() {

    val data = MutableLiveData<List<Food>>()
    val order = MutableLiveData<List<FoodOrderModel>>()
    val actionOrder = MutableLiveData<Action<Void>>()
    val textToolbar = MutableLiveData<String>().apply { value = category.name }

    init {
        api.getFoods("0UDfSY7pyQ", category.id)
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })

        api.newOrder
                .applySchedulers()
                .subscribe({
                    order.postValue(it)
                }, {
            it.printStackTrace()
        })

    }

    fun orderClicked() {
        actionOrder.postValue(Action())
    }

    fun orderSelected(food: Food) {
        api.addFoodToOrder(food)
    }

}