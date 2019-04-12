package com.kserno.foodie.accounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.User
import com.kserno.foodie.R
import com.kserno.foodie.databinding.ItemAccountBinding

/**
 *  Created by filipsollar on 2019-04-10
 */
class AccountAdapter: Adapter<User>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder<User> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): Adapter.ViewHolder<User>(itemView) {

        val binding = DataBindingUtil.bind<ItemAccountBinding>(itemView)

        override fun update(item: User) {
            binding?.user = item
            binding?.executePendingBindings()
        }

    }
}