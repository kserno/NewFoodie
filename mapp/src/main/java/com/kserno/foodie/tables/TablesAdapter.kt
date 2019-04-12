package com.kserno.foodie.tables

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.kserno.foodie.common.Adapter
import app.kserno.foodie.common.model.Table
import com.kserno.foodie.R
import com.kserno.foodie.databinding.ItemTableBinding

/**
 *  Created by filipsollar on 2019-04-10
 */
class TablesAdapter: Adapter<Table>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Table> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return TableViewHolder(view)
    }

    class TableViewHolder(itemView: View): ViewHolder<Table>(itemView) {

        val binding = DataBindingUtil.bind<ItemTableBinding>(itemView)

        override fun update(item: Table) {
            binding?.table = item
            binding?.executePendingBindings()
        }

    }


}