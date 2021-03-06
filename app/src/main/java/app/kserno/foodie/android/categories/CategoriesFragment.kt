package app.kserno.foodie.android.categories

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.android.food.FoodCategoriesAdapter
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentCategoriesBinding
import app.kserno.foodie.android.food.FoodFragmentDirections
import app.kserno.foodie.common.VerticalSpaceItemDecoration
import app.kserno.foodie.common.api.ParseApi
import app.kserno.foodie.common.model.FoodCategory
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-03-27
 */
class CategoriesFragment:BaseFragment(), Adapter.Listener<FoodCategory> {

    override val layoutId: Int = R.layout.fragment_categories


    override fun onItemSelected(item: FoodCategory) {
        val dirs = CategoriesFragmentDirections.actionCategoriesFragmentToFoodFragment(item.id)
        findNavController().navigate(dirs)
    }

    lateinit var viewModel: CategoriesViewModel
    lateinit var adapter: FoodCategoriesAdapter
    @Inject lateinit var parseApi: ParseApi

    lateinit var binding: FragmentCategoriesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!

        adapter = FoodCategoriesAdapter()
        adapter.listener = this
        viewModel = CategoriesViewModel(parseApi)

        binding.viewModel = viewModel
        recyclerView.adapter = adapter


        viewModel.data.observe(this, Observer {
            adapter.items = it
        })
        viewModel.order.observe(this, Observer {
            val sum = it.sumByDouble { model -> model.count * model.food.price }
            tvSum.text = "Total: $sum €"
            if (it.isEmpty()) {
                orderLayout.visibility = View.GONE
            } else {
                orderLayout.visibility = View.VISIBLE
            }
        })

        viewModel.actionOrder.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = CategoriesFragmentDirections.actionCategoriesFragmentToNewOrderFragment()
                findNavController().navigate(dirs)
            }
        })
        orderLayout.setOnClickListener {
            viewModel.orderClicked()
        }

        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}