package app.kserno.foodie.android.neworder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.FoodOrder
import app.kserno.foodie.common.model.FoodOrderModel
import app.kserno.foodie.common.model.FoodWs
import java.util.*

/**
 *  Created by filipsollar on 2019-04-12
 */
class NewOrderViewModel(
        private val api: Api,
        private val wsService: WsService
): ViewModel() {

    val data = MutableLiveData<List<FoodOrderModel>>()
    val actionDone = MutableLiveData<Action<Void>>()

    init {
        api.newOrder
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }


    fun placeOrderClicked() {
        val list = data.value!!
        val result = ArrayList<FoodOrder>()

        list.forEach {
            for (i in 0..it.count-1) {
                result.add(FoodOrder(
                        UUID.randomUUID().toString(),
                        FoodWs(
                                it.food.name,
                                it.food.price,
                                it.food.photoUrl
                        )
                ))
            }
        }
        wsService.order(result)
    }



}