package com.kserno.foodie.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.api.Api
import com.kserno.foodie.PersistentLayer
import com.kserno.foodie.R
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.databinding.FragmentMainBinding
import com.squareup.moshi.Moshi
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-03
 */
class MainFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_main


    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding

    @Inject lateinit var api: Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)
        binding = DataBindingUtil.bind(view)!!

        viewModel = MainViewModel(api)
        binding.viewModel = viewModel
        bindActions()
    }

    private fun bindActions() {
        viewModel.actionAccounts.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = MainFragmentDirections.actionMainFragmentToAccountsFragment()
                findNavController().navigate(dirs)
            }
        })
        viewModel.actionFoods.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = MainFragmentDirections.actionMainFragmentToCategoriesFragment()
                findNavController().navigate(dirs)
            }
        })
        viewModel.actionTables.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = MainFragmentDirections.actionMainFragmentToTablesFragment()
                findNavController().navigate(dirs)
            }
        })
    }
}