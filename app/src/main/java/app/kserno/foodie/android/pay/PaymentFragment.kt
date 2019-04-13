package app.kserno.foodie.android.pay

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentPaymentBinding
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.model.FoodOrder
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */
class PaymentFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_payment

    lateinit var viewModel: PaymentViewModel

    lateinit var binding: FragmentPaymentBinding

    @Inject lateinit var wsService: WsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        binding = DataBindingUtil.bind(view)!!

        val data = arguments?.getParcelableArrayList<FoodOrder>("data") as List<FoodOrder>

        viewModel = PaymentViewModel(wsService, data)
        binding.viewModel = viewModel
    }
}