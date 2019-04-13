package app.kserno.foodie.android.paid

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.common.WsService
import kotlinx.android.synthetic.main.fragment_paid.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */
class PaidFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_paid

    lateinit var viewModel: PaidViewModel

    @Inject lateinit var wsService: WsService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity?.component?.inject(this)

        val adapter = PaidAdapter()

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

        viewModel = PaidViewModel(wsService)
        viewModel.data.observe(this, Observer {
            adapter.items = it
        })

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}