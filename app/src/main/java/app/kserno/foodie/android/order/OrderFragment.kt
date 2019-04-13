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
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
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

    var observationHandler: ProximityObserver.Handler? = null

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
        setupEstimote()
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
                mainActivity?.controller?.navigate(dirs)
            }
        })
    }

    private fun setupEstimote() {


        RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(
                activity!!, { buildProximityObserver() },
        { print("log") },
        { it.printStackTrace()})
    }

    private fun buildProximityObserver() {
        val cloudCredentials = EstimoteCloudCredentials("food-io-fy8", "bb3bdfa5d6fa4c24ac0e1fb9350e2dc0")
        val proximityObserver = ProximityObserverBuilder(context!!, cloudCredentials)
                .withBalancedPowerMode()
                .onError { it.printStackTrace()}
                .build()

        val tableZone = ProximityZoneBuilder()
                .forTag("table")
                .inCustomRange(2.0)
                .onEnter {
                    print(it)
                    /* do something here */
                }
                .onExit {
                    print(it)
                    /* do something here */}
                .onContextChange {/* do something here */}
                .build()

        observationHandler = proximityObserver.startObserving(tableZone)

    }

    override fun onDestroy() {
        observationHandler?.stop()
        super.onDestroy()
    }
}

class OrderFactory(val api: Api, val wsService: WsService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrderViewModel(api, wsService) as T
    }

}