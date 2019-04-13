package app.kserno.foodie.common

import android.content.Context
import android.provider.Settings

/**
 *  Created by filipsollar on 2019-04-13
 */
object Utils {

    fun getUniqueId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}