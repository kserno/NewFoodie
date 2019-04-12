package app.kserno.foodie.android.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.kserno.foodie.android.R
import kotlinx.android.synthetic.main.fragment_order.*

/**
 *  Created by filipsollar on 2019-03-27
 */
class OrderFragment: Fragment() {

    lateinit var viewModel: OrderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = OrderViewModel(api)

        btOrder.setOnClickListener {
            val dirs = OrderFragmentDirections.actionOrderFragmentToCategoriesFragment()
            findNavController().navigate(dirs)
        }
    }
}