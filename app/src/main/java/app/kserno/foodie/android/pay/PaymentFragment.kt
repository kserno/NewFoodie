package app.kserno.foodie.android.pay

import android.os.Bundle
import android.view.View
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.common.WsService
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */
class PaymentFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_payment

    lateinit var viewModel: PaymentViewModel

    @Inject lateinit var wsService: WsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        viewModel = PaymentViewModel(wsService)

    }
}