package com.kserno.foodie.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.VerticalSpaceItemDecoration

import app.kserno.foodie.common.api.ParseApi
import app.kserno.foodie.common.model.FoodCategory
import com.kserno.foodie.R
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 *  Created by filipsollar on 2019-03-27
 */
class CategoriesFragment: Fragment(), Adapter.Listener<FoodCategory> {

    override fun onItemSelected(item: FoodCategory) {
        val dirs = CategoriesFragmentDirections.actionCategoriesFragmentToFoodsFragment(item)
        findNavController().navigate(dirs)
    }

    lateinit var viewModel: CategoriesViewModel
    lateinit var adapter: FoodCategoriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FoodCategoriesAdapter()
        adapter.listener = this
        viewModel = CategoriesViewModel(ParseApi(context!!))

        recyclerView.adapter = adapter

        viewModel.data.observe(this, Observer {
            adapter.items = it
        })

        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}