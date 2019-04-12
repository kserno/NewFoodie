package app.kserno.foodie.android.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        viewModel = OrderViewModel(api, wsService)

        btOrder.setOnClickListener {
            val dirs = OrderFragmentDirections.actionOrderFragmentToCategoriesFragment()
            findNavController().navigate(dirs)
        }
    }
}