package com.kserno.foodie.addtable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.databinding.DialogAddTableBinding

/**
 *  Created by filipsollar on 2019-04-03
 */
class AddTableDialog: DialogFragment() {

    lateinit var viewModel: AddTableViewModel
    lateinit var binding: DialogAddTableBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!
        viewModel = AddTableViewModel(ParseApi(context!!))

        binding.viewModel = viewModel
    }
}