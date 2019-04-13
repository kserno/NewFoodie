package app.kserno.foodie.android.detail

import androidx.lifecycle.MutableLiveData
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodOrder
import io.reactivex.Single

/**
 *  Created by filipsollar on 2019-03-28
 */
class FoodDetailViewModel(val api: Api, foodIn: Food) {

    val food = MutableLiveData<Food>().apply { value = foodIn }

    val actionAdded = MutableLiveData<Action<Void>>()

    fun orderClicked() {
        api.addFoodToOrder(food.value!!)
        actionAdded.postValue(Action())
    }

}