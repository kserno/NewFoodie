package com.kserno.foodie.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kserno.foodie.R

/**
 *  Created by filipsollar on 2019-04-11
 */
class LoadingDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_progress, container, false)
    }
}