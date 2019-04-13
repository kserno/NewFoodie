package com.kserno.foodie.login

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
import app.kserno.foodie.common.dialog.InfoDialog
import com.kserno.foodie.PersistentLayer
import com.kserno.foodie.R
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.databinding.FragmentLoginBinding
import com.kserno.foodie.dialog.LoadingDialog
import com.squareup.moshi.Moshi
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-03
 */
class LoginFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_login

    lateinit var viewModel: LoginViewModel
    lateinit var binding : FragmentLoginBinding

    @Inject lateinit var api: Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!
        viewModel = LoginViewModel(api)
        binding.viewModel = viewModel

        viewModel.actionNext.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                findNavController().navigate(dirs)
            }
        })

        viewModel.actionError.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                InfoDialog.create("Error", "Invalid username/ password combination")
                        .show(fragmentManager!!, "1")
            }
        })


    }
}