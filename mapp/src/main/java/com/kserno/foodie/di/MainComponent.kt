package com.kserno.foodie.di

import com.kserno.foodie.MainActivity
import dagger.Component

/**
 *  Created by filipsollar on 2019-04-12
 */
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

}