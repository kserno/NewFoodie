package app.kserno.foodie.android.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Order
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 *  Created by filipsollar on 2019-03-28
 */
class OrderViewModel(
        private val api: Api,
        private val wsService: WsService
): ViewModel() {

    val data = MutableLiveData<Order>()

    val actionOrder = MutableLiveData<Action<Void>>()
    val actionPaid = MutableLiveData<Action<Void>>()
    val actionClose = MutableLiveData<Action<Void>>()
    val actionPay = MutableLiveData<Action<Void>>()




    init {
        wsService.getObservable()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }


    fun orderClicked() {
        actionOrder.postValue(Action())
    }

    fun paidClicked() {
        actionPaid.postValue(Action())
    }

    fun closeClicked() {
        //actionClose.postValue(Action())
    }

    fun payClicked() {
        actionPay.postValue(Action())
    }


}