package app.kserno.foodie.android.paying

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentPayingBinding
import app.kserno.foodie.common.WsService
import kotlinx.android.synthetic.main.fragment_paying.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */
class PayingFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_paying

    @Inject lateinit var wsService: WsService

    lateinit var viewModel: PayingViewModel
    lateinit var binding: FragmentPayingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        mainActivity?.component?.inject(this)

        viewModel = PayingViewModel(wsService)

        val adapter = PayingAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context!!, 2)

        viewModel.data.observe(this, Observer {
            adapter.items = it.orders.filter { it.paidBy == null }
        })

        viewModel.actionGateway.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val bundle = Bundle().apply { putParcelableArrayList("data", ArrayList(adapter.getSelected()))}
                findNavController().navigate(R.id.action_payingFragment_to_paymentFragment, bundle)
            }
        })

        binding.viewModel = viewModel


    }


}