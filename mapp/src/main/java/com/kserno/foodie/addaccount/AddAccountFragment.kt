package com.kserno.foodie.addaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.databinding.FragmentAddAccountBinding
import com.kserno.foodie.dialog.LoadingDialog

/**
 *  Created by filipsollar on 2019-04-10
 */
class AddAccountFragment: Fragment() {

    lateinit var binding : FragmentAddAccountBinding
    lateinit var viewModel : AddAccountViewModel

    var dialog: LoadingDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!
        viewModel = AddAccountViewModel(ParseApi(context!!))
        binding.viewModel = viewModel

        viewModel.actionCreated.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                dialog?.dismiss()
                findNavController().navigateUp()
            }
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                dialog = LoadingDialog()
                dialog?.show(fragmentManager!!, "")
            }
        })
    }
}