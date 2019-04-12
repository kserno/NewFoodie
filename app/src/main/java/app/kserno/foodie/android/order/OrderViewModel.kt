package app.kserno.foodie.android.order

import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.applySchedulers
import app.kserno.foodie.common.model.Order
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 *  Created by filipsollar on 2019-03-28
 */
class OrderViewModel(
        private val api: Api
) {

    private val statusPublisher = BehaviorSubject.create<Status>()

    val status: Observable<Status>
        get() = statusPublisher.applySchedulers()

    private val orderSubject = BehaviorSubject.create<Order>()

    val order: Observable<Order>
        get() = orderSubject.applySchedulers()

    init {
        statusPublisher.onNext(Status.ORDER)
    }

    enum class Status {
        NO_ORDER,
        ORDER
    }

}