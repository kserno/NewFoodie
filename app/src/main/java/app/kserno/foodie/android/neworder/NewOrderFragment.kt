package app.kserno.foodie.android.neworder

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentNewOrderBinding
import app.kserno.foodie.android.dialog.InfoDialog
import app.kserno.foodie.common.WsService
import app.kserno.foodie.common.api.Api
import kotlinx.android.synthetic.main.fragment_new_order.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */
class NewOrderFragment: BaseFragment() {

    lateinit var viewModel: NewOrderViewModel

    @Inject lateinit var api: Api
    @Inject lateinit var wsService: WsService

    lateinit var binding: FragmentNewOrderBinding

    override val layoutId: Int = R.layout.fragment_new_order

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!


        mainActivity?.component?.inject(this)
        val adapter = NewOrderAdapter()
        recyclerView.adapter = adapter

        viewModel = NewOrderViewModel(api, wsService)

        viewModel.data.observe(this, Observer {
            adapter.items = it
        })
        viewModel.actionDone.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                //InfoDialog.create("Bon Appetit!", "Your order will be delivered shortly. :-)")
                //        .show(childFragmentManager, "tag")
                findNavController().popBackStack(R.id.orderFragment, true)

            }
        })

        binding.viewModel = viewModel
    }
}