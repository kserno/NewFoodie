package app.kserno.foodie.android.pay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.model.FoodOrder

/**
 *  Created by filipsollar on 2019-04-12
 */
class PaymentViewModel(val wsService: WsService, val data: List<FoodOrder>) : ViewModel() {

    val actionDone = MutableLiveData<Action<Void>>()
    val textPay = MutableLiveData<String>().apply {
        val sum = data.sumByDouble { it.food.price }
        value = "Pay ( $sum €)"
    }

    val textBy = MutableLiveData<String>()

    init {

    }


    fun payClicked() {
        if (textBy.value.isNullOrEmpty()) {
            return
        }
        wsService.pay(data, textBy.value!!)
        actionDone.postValue(Action())
    }

}