package com.kserno.foodie

import android.content.Context
import app.kserno.foodie.common.model.User
import com.squareup.moshi.Moshi

/**
 *  Created by filipsollar on 2019-04-10
 */
class PersistentLayer(context: Context, private val moshi: Moshi) {

    companion object {
        const val PREFS_NAME = "Foodie_prefs"
        const val FIELD_USER = "FIELD_USER"
    }

    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var currentUser: User?
    set(value) {
        val str = if ( value == null) {
            ""
        } else {
            moshi.adapter<User>(User::class.java).toJson(value)
        }
        sharedPrefs.edit()
                .putString(FIELD_USER, str)
                .commit()
    }
    get() {
        val str = sharedPrefs.getString(FIELD_USER, "")
        if (str.isEmpty()) {
            return null
        }
        return moshi.adapter<User>(User::class.java).fromJson(str)
    }

}