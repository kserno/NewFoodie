package com.kserno.foodie.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import com.kserno.foodie.R
import com.kserno.foodie.addtable.AddTableDialog
import com.kserno.foodie.base.BaseFragment
import com.kserno.foodie.databinding.FragmentTablesBinding
import kotlinx.android.synthetic.main.fragment_tables.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-03
 */
class TablesFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_tables

    lateinit var viewModel: TablesViewModel
    lateinit var binding: FragmentTablesBinding

    @Inject lateinit var api: Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)
        binding = DataBindingUtil.bind(view)!!

        viewModel = TablesViewModel(api)
        binding.viewModel = viewModel

        val adapter = TablesAdapter()
        recyclerView.adapter = adapter
        viewModel.data.observe(this, Observer {
            adapter.items = it
        })
        viewModel.actionNew.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dialog = AddTableDialog()
                dialog.show(fragmentManager!!, "0")
            }
        })
    }
}