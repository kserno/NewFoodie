package app.kserno.foodie.android.paid

import androidx.lifecycle.MutableLiveData
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.FoodOrder

/**
 *  Created by filipsollar on 2019-04-12
 */
class PaidViewModel(
        private val wsService: WsService
) {

    val data = MutableLiveData<List<FoodOrder>>()

    init {
        wsService.getObservable()
                .applySchedulers()
                .subscribe({
                    data.postValue(it.orders.filter { it.paidBy != null })
                }, {
                    it.printStackTrace()
                })
    }


}