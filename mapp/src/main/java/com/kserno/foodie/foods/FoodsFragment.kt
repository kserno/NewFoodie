package com.kserno.foodie.foods

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
import com.kserno.foodie.databinding.FragmentFoodsBinding
import kotlinx.android.synthetic.main.fragment_foods.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-03
 */
class FoodsFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_foods


    lateinit var viewModel: FoodsViewModel
    lateinit var binding: FragmentFoodsBinding

    @Inject lateinit var api: Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!
        viewModel = FoodsViewModel(api)
        binding.viewModel = viewModel

        val adapter = FoodsAdapter()
        recyclerView.adapter = adapter

        viewModel.actionNew.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = FoodsFragmentDirections.actionFoodsFragmentToAddFoodFragment()
                findNavController().navigate(dirs)
            }
        })
        viewModel.data.observe(this, Observer {
            adapter.items = it
        })
    }
}