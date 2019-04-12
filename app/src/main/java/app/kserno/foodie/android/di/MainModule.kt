package app.kserno.foodie.android.di

import android.content.Context
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  Created by filipsollar on 2019-04-12
 */
@Module
class MainModule(
        @get:Provides @get:Singleton val context: Context
) {

    @Singleton
    @Provides
    fun providesApi(api: ParseApi): Api {
        return api
    }

    @Singleton
    @Provides
    fun providesParseApi(context: Context) : ParseApi {
        return ParseApi(context)
    }

    @Singleton
    @Provides
    fun providesWsService(moshi: Moshi) : WsService {
        return WsService(moshi)
    }

    @Singleton
    @Provides
    fun providesMoshi() : Moshi {
        return Moshi.Builder().build()
    }

}