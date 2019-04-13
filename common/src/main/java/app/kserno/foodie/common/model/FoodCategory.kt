package app.kserno.foodie.common.model

import android.os.Parcelable
import com.parse.ParseObject
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-03-27
 */
@Parcelize
data class FoodCategory(
        val id: String,
        val name: String,
        val imageUrl: String
) : Parcelable{
    companion object {
        internal fun fromParseObject(parseObject: ParseObject): FoodCategory {
            return FoodCategory(
                    parseObject.objectId,
                    parseObject.getString("name") ?: "",
                    parseObject.getString("imageUrl") ?: ""
            )
        }
    }
}
