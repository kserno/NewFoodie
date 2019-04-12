package app.kserno.foodie.android.order

import androidx.lifecycle.MutableLiveData
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
) {

    val data = MutableLiveData<Order>()

    init {
        wsService.getObservable()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }




}