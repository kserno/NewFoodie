package app.kserno.foodie.android

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 *  Created by filipsollar on 2019-04-11
 */
class Foodie: Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}