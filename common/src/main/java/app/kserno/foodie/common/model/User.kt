package app.kserno.foodie.common.model

import android.os.Parcelable
import com.parse.ParseObject
import com.parse.ParseUser
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 2019-03-27
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class User(
        val name: String,
        val username: String,
        val role: Role,
        val restaurantId: String
): Parcelable {
    enum class Role {
        MANAGER,
        WAITER
    }

    companion object {
        internal fun fromParseObject(parseObject: ParseObject) : User {
            return User(
                    parseObject.getString("name")?: "",
                    parseObject.getString("username") ?: "",
                    if (parseObject.getString("role") == "manager") Role.MANAGER else Role.WAITER,
                    parseObject.getString("restaurantId") ?: ""

            )
        }
    }

}


internal fun ParseUser.toUser(): User {
    return User(
            this.getString("name") ?: "",
            this.username,
            if (getString("role") == "manager") User.Role.MANAGER else User.Role.WAITER,
            this.getString("restaurantId") ?: ""
    )
}