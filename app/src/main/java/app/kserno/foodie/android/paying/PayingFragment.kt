package app.kserno.foodie.android.paying

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentPayingBinding
import app.kserno.foodie.common.WsService
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

        viewModel.actionGateway.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                val dirs = PayingFragmentDirections.actionPayingFragmentToPaymentFragment()
                findNavController().navigate(dirs)
            }
        })

        binding.viewModel = viewModel


    }
}