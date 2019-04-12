package com.kserno.foodie.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.databinding.FragmentAccountsBinding
import kotlinx.android.synthetic.main.fragment_accounts.*

/**
 *  Created by filipsollar on 2019-04-03
 */
class AccountsFragment: Fragment() {

    lateinit var viewModel: AccountsViewModel
    lateinit var binding: FragmentAccountsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = AccountsViewModel(ParseApi(context!!))
        binding.viewModel = viewModel

        val adapter = AccountAdapter()
        recyclerView.adapter = adapter

        viewModel.users.observe(this, Observer {
            adapter.items = it
        })
    }
}