package app.kserno.foodie.android.neworder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.kserno.foodie.common.Action
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.FoodOrderModel

/**
 *  Created by filipsollar on 2019-04-12
 */
class NewOrderViewModel(
        private val api: Api
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


    fun doneClicked() {

    }


}