package app.kserno.foodie.android.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.kserno.foodie.android.R
import app.kserno.foodie.android.base.BaseFragment
import app.kserno.foodie.android.databinding.FragmentFoodDetailBinding
import app.kserno.foodie.android.dialog.InfoDialog
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.model.Food
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_food_detail.*
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-03-28
 */
class FoodDetailFragment: BaseFragment() {

    @Inject lateinit var api: Api

    lateinit var viewModel: FoodDetailViewModel
    lateinit var binding: FragmentFoodDetailBinding


    override val layoutId: Int = R.layout.fragment_food_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val food = arguments?.getParcelable<Food>("coffee")
        binding = DataBindingUtil.bind(view)!!

        mainActivity?.component?.inject(this)

        viewModel = FoodDetailViewModel(api, food!!)
        binding.viewModel = viewModel

        viewModel.actionAdded.observe(this, Observer {
            if (!it.hasBeenHandled) {
                it.getContentIfNotHandled()
                InfoDialog().show(fragmentManager!!, "1")
            }
        })

    }
}