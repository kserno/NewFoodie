package app.kserno.foodie.common.model

import android.os.Parcelable
import com.parse.ParseObject
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-03-27
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class Food(
        val id: String,
        val name: String,
        val description: String,
        val photoUrl: String,
        val price: Double
): Parcelable {
    companion object {
        internal fun fromParseObject(parseObject: ParseObject) :Food {
            return Food(
                    parseObject.objectId,
                    parseObject.getString("name") ?: "",
                    parseObject.getString("description") ?: "",
                    parseObject.getString("photoUrl") ?: "",
                    parseObject.getDouble("price") ?: 0.0
            )
        }
    }
}