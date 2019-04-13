package app.kserno.foodie.android.paid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.android.R
import app.kserno.foodie.android.databinding.ItemPaidBinding
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.FoodOrder

class PaidAdapter : Adapter<FoodOrder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<FoodOrder> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paid, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<FoodOrder>(itemView) {

        val binding = DataBindingUtil.bind<ItemPaidBinding>(itemView)

        override fun update(item: FoodOrder) {
            binding?.food = item.food
            binding?.foodOrder = item
            binding?.executePendingBindings()
        }


    }

}
