package app.kserno.foodie.android.di

import app.kserno.foodie.android.categories.CategoriesFragment
import app.kserno.foodie.android.detail.FoodDetailFragment
import app.kserno.foodie.android.food.FoodFragment
import app.kserno.foodie.android.main.MainActivity
import app.kserno.foodie.android.neworder.NewOrderFragment
import app.kserno.foodie.android.order.OrderFragment
import app.kserno.foodie.android.paid.PaidFragment
import app.kserno.foodie.android.pay.PaymentFragment
import app.kserno.foodie.android.paying.PayingFragment
import dagger.Component
import javax.inject.Singleton

/**
 *  Created by filipsollar on 2019-04-12
 */
@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(foodDetailFragment: FoodDetailFragment)
    fun inject(foodFragment: FoodFragment)
    fun inject(newOrderFragment: NewOrderFragment)
    fun inject(orderFragment: OrderFragment)
    fun inject(payingFragment: PayingFragment)
    fun inject(paymentFragment: PaymentFragment)
    fun inject(paidFragment: PaidFragment)

}