package app.kserno.foodie.common.api

import android.graphics.Bitmap
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodCategory
import app.kserno.foodie.common.model.FoodOrderModel
import app.kserno.foodie.common.model.Order
import app.kserno.foodie.common.model.Table
import app.kserno.foodie.common.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File
import java.util.concurrent.CompletableFuture

/**
 *  Created by filipsollar on 2019-03-27
 */
interface Api {

    val newOrder: Observable<List<FoodOrderModel>>


    fun getCurrentUser(): User?

    fun signOut(): Completable

    fun getFoodCategories(): Single<List<FoodCategory>>
    fun getFoods(restaurantId: String, categoryId: String): Single<List<Food>>


    fun login(username: String, password: String): Single<User>

    fun getTables(restaurantId: String): Single<List<Table>>

    fun getOrderByBeacon(beaconId: String): Observable<Order>

    fun getUsers(restaurantId: String): Single<List<User>>

    fun addTable(name: String, beaconId: String): Completable

    fun updateTable(table: Table): Completable

    fun addAccount(name: String, username: String, password: String): Completable

    fun addFood(name: String, description: String, price: Double, categoryId: String, image: File) : Completable

    fun cancelOrder()

    fun addFoodToOrder(food: Food)

}