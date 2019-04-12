package app.kserno.foodie.common.model

import com.parse.ParseObject

/**
 *  Created by filipsollar on 2019-03-27
 */
data class FoodCategory(
        val id: String,
        val name: String,
        val imageUrl: String
) {
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
