package app.kserno.foodie.android.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentFoodBinding
import app.kserno.foodie.android.databinding.FragmentFoodDetailBinding
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.api.ParseApi
import app.kserno.foodie.common.model.Food
import kotlinx.android.synthetic.main.fragment_food.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-03-27
 */
class FoodFragment: BaseFragment(), FoodAdapter.Listener {
    override fun onOrderClicked(food: Food) {
        viewModel.orderSelected(food)
    }

    lateinit var viewModel: FoodViewModel
    lateinit var adapter: FoodAdapter
    @Inject lateinit var api: Api

    override val layoutId: Int = R.layout.fragment_food

    lateinit var binding: FragmentFoodBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!

        val args = FoodFragmentArgs.fromBundle(arguments!!)

        adapter = FoodAdapter()
        viewModel = FoodViewModel(api, args.category)
        binding.viewModel = viewModel

        adapter.listener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

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
                val dirs = FoodFragmentDirections.actionFoodFragmentToNewOrderFragment()
                findNavController().navigate(dirs)
            }
        })
        orderLayout.setOnClickListener {
            viewModel.orderClicked()
        }

    }
    override fun onItemSelected(item: Food) {
        findNavController().navigate(R.id.action_foodFragment_to_foodDetailFragment,
                Bundle().apply { putParcelable("coffee", item) }
        )
    }
}