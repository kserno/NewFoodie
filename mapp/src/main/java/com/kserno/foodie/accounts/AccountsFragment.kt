package com.kserno.foodie.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.common.VerticalSpaceItemDecoration
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.databinding.FragmentAccountsBinding
import kotlinx.android.synthetic.main.fragment_accounts.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-03
 */
class AccountsFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_accounts


    lateinit var viewModel: AccountsViewModel
    lateinit var binding: FragmentAccountsBinding

    @Inject lateinit var api: Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        mainActivity?.component?.inject(this)

        viewModel = AccountsViewModel(api)
        binding.viewModel = viewModel

        val adapter = AccountAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))

        viewModel.users.observe(this, Observer {
            adapter.items = it
        })

        viewModel.actionNew.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = AccountsFragmentDirections.actionAccountsFragmentToAddAccountFragment()
                findNavController().navigate(dirs)
            }
        })
    }
}