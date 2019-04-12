package app.kserno.foodie.common.model

import android.os.Parcelable
import app.kserno.foodie.common.model.Food
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-04-12
 */
@Parcelize
data class FoodOrderModel(
        val food: Food,
        var count: Int
): Parcelable