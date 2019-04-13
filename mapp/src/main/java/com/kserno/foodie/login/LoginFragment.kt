package com.kserno.foodie.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.PersistentLayer
import com.kserno.foodie.R
import com.kserno.foodie.databinding.FragmentLoginBinding
import com.kserno.foodie.dialog.LoadingDialog
import com.squareup.moshi.Moshi

/**
 *  Created by filipsollar on 2019-04-03
 */
class LoginFragment: Fragment() {


    lateinit var viewModel: LoginViewModel
    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!
        viewModel = LoginViewModel(ParseApi(context!!), PersistentLayer(context!!, Moshi.Builder().build()))
        binding.viewModel = viewModel

        viewModel.actionNext.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                findNavController().navigate(dirs)
            }
        })


    }
}