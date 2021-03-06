package app.kserno.foodie.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-03-27
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class FoodOrder(
        val id: String,
        val food: FoodWs,
        var paidBy: String? = null
): Parcelable {




}