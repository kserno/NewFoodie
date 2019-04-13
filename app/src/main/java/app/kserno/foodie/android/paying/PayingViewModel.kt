package app.kserno.foodie.android.paying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Order

/**
 *  Created by filipsollar on 2019-04-12
 */
class PayingViewModel(
        private val wsService: WsService
): ViewModel() {

    val data = MutableLiveData<Order>()
    val actionGateway = MutableLiveData<Action<Void>>()

    init {
        wsService.getObservable()
                .applySchedulers()
                .subscribe({
                    data.postValue(it)
                }, {
                    it.printStackTrace()
                })
    }

    fun goToGatewayClicked() {
        actionGateway.postValue(Action())
    }


}