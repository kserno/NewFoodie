package app.kserno.foodie.common.model

import android.os.Parcelable
import com.parse.ParseObject
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-04-04
 */
@Parcelize
data class Table(
        val id: String,
        val name: String,
        val beaconId: String,
        val restaurantId: String
): Parcelable {
    companion object {
        internal fun fromParseObject(parseObject: ParseObject) : Table {
            return Table(
                    parseObject.objectId,
                    parseObject.getString("name") ?: "",
                    parseObject.getString("beaconId") ?: "",
                    parseObject.getString("restaurantId") ?: ""
            )
        }
    }
}