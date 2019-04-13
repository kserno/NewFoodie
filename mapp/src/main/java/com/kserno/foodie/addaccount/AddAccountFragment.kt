package com.kserno.foodie.addaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.databinding.FragmentAddAccountBinding
import com.kserno.foodie.dialog.LoadingDialog
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-10
 */
class AddAccountFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_add_account


    lateinit var binding : FragmentAddAccountBinding
    lateinit var viewModel : AddAccountViewModel

    @Inject lateinit var api: Api

    var dialog: LoadingDialog? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!
        viewModel = AddAccountViewModel(api)
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