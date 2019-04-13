package com.kserno.foodie.di

import com.kserno.foodie.MainActivity
import com.kserno.foodie.accounts.AccountsFragment
import com.kserno.foodie.addaccount.AddAccountFragment
import com.kserno.foodie.addfood.AddFoodFragment
import com.kserno.foodie.addtable.AddTableDialog
import com.kserno.foodie.categories.CategoriesFragment
import com.kserno.foodie.foods.FoodsFragment
import com.kserno.foodie.login.LoginFragment
import com.kserno.foodie.main.MainFragment
import com.kserno.foodie.table.TableFragment
import com.kserno.foodie.tables.TablesFragment
import dagger.Component
import javax.inject.Singleton

/**
 *  Created by filipsollar on 2019-04-12
 */
@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
    fun inject(accountsFragment: AccountsFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(addAccountFragment: AddAccountFragment)
    fun inject(addFoodFragment: AddFoodFragment)
    fun inject(addTableDialog: AddTableDialog)
    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(foodsFragment: FoodsFragment)
    fun inject(tablesFragment: TablesFragment)
    fun inject(tableFragment: TableFragment)


}