package com.kserno.foodie.table

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.model.Table
import com.kserno.foodie.R
import com.kserno.foodie.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_table.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-13
 */
class TableFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_table

    lateinit var viewModel: TableViewModel
    @Inject lateinit var wsService: WsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity?.component?.inject(this)

        viewModel = TableViewModel(wsService)

        val adapter = TableAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context!!, 2)

        viewModel.data.observe(this, Observer {
            adapter.items = it.orders
        })
    }
}