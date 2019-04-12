package app.kserno.foodie.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.kserno.foodie.android.categories.CategoriesViewModel
import app.kserno.foodie.android.detail.FoodDetailViewModel
import app.kserno.foodie.android.neworder.NewOrderViewModel
import app.kserno.foodie.android.order.OrderFragment
import app.kserno.foodie.android.order.OrderViewModel
import app.kserno.foodie.android.paying.PayingViewModel
import app.kserno.foodie.common.api.Api
import app.kserno.foodie.common.model.Food
import javax.inject.Inject

/**
 *  Created by filipsollar on 2019-04-12
 */

class ViewModelStore(
        val api: Api
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> OrderViewModel(api)
            modelClass.isAssignableFrom(PayingViewModel::class.java) -> PayingViewModel(api)
            modelClass.isAssignableFrom(NewOrderViewModel::class.java) -> NewOrderViewModel(api)
            modelClass.isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(api)
            else -> throw IllegalArgumentException()
        } as T
    }

}
/*

class IdViewModelStore @Inject constructor(
        private val api: Api,
        private val id: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom()) {
            return CategoriesViewModel()
        } else if (modelClass.isAssignableFrom()) {
            throw IllegalArgumentException()
        }
    }

}

class FoodViewModelStore @Inject constructor(
        private val food: Food
): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodDetailViewModel::class.java)) {
            return FoodDetailViewModel(food) as T
        }else {
            throw IllegalArgumentException()
        }
    }
}*/
