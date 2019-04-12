package app.kserno.foodie.android.neworder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.android.R
import app.kserno.foodie.android.databinding.ItemFoodOrderBinding
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.FoodOrderModel

/**
 *  Created by filipsollar on 2019-04-12
 */
class NewOrderAdapter: Adapter<FoodOrderModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<FoodOrderModel> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_order, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : Adapter.ViewHolder<FoodOrderModel>(itemView) {

        val binding = DataBindingUtil.bind<ItemFoodOrderBinding>(itemView)


        override fun update(item: FoodOrderModel) {
            binding?.food = item.food
            binding?.foodOrder = item
            binding?.executePendingBindings()
        }

    }
}