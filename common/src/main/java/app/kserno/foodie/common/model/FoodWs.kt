package app.kserno.foodie.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class FoodWs(
        val name: String,
        val price: Double,
        val photoUrl: String
): Parcelable {

    val prettyPrice: String
        get() = "$price €"

}