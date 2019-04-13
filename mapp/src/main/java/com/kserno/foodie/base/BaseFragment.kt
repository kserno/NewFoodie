package com.kserno.foodie.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.kserno.foodie.MainActivity
import com.kserno.foodie.di.MainComponent

/**
 *  Created by filipsollar on 2019-04-12
 */
abstract class BaseFragment: Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int


    val mainActivity: MainActivity?
        get() = activity as? MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }



}