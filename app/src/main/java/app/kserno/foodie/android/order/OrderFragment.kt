package app.kserno.foodie.android.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentOrderBinding
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-03-27
 */
class OrderFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_order


    lateinit var viewModel: OrderViewModel

    @Inject lateinit var api: Api
    @Inject lateinit var wsService: WsService

    lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!

        val adapter = OrderAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context!!, 2)

        viewModel = ViewModelProviders.of(this, OrderFactory(api, wsService)).get(OrderViewModel::class.java)


        viewModel.data.observe(this, Observer {
            if (it.orders.isEmpty()) {
                layoutNothingOrdered.visibility = View.VISIBLE
                layoutOrdered.visibility = View.GONE
                layoutAllPaid.visibility = View.GONE
            } else if (it.orders.none { it.paidBy == null }) {
                layoutNothingOrdered.visibility = View.GONE
                layoutOrdered.visibility = View.GONE
                layoutAllPaid.visibility = View.VISIBLE
            } else {
                layoutNothingOrdered.visibility = View.GONE
                layoutOrdered.visibility = View.VISIBLE
                layoutAllPaid.visibility = View.GONE
            }

            adapter.items = it.orders.filter { it.paidBy == null }


        })

        bindActions()
        binding.viewModel = viewModel
    }

    private fun bindActions() {
        viewModel.actionOrder.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = OrderFragmentDirections.actionOrderFragmentToCategoriesFragment()
                findNavController().navigate(dirs)
            }
        })
        viewModel.actionPaid.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = OrderFragmentDirections.actionOrderFragmentToPaidFragment()
                findNavController().navigate(dirs)
            }
        })
        viewModel.actionPay.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = OrderFragmentDirections.actionOrderFragmentToPayingFragment()
                findNavController().navigate(dirs)
            }
        })
    }
}

class OrderFactory(val api: Api, val wsService: WsService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrderViewModel(api, wsService) as T
    }

}