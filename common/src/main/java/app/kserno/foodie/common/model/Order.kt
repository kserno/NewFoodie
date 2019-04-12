package app.kserno.foodie.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-03-27
 */
@Parcelize
data class Order(
        val id: String,
        val paying: Boolean,
        val status: Status,
        val orders: List<FoodOrder>
): Parcelable {

    enum class Status {
        NOT_STARTED, RUNNING
    }
}