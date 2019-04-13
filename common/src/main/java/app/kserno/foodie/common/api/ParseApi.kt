package app.kserno.foodie.common.api

import android.content.Context
import android.graphics.Bitmap
import app.kserno.foodie.common.model.Food
import app.kserno.foodie.common.model.FoodCategory
import app.kserno.foodie.common.model.FoodOrderModel
import app.kserno.foodie.common.model.Order
import app.kserno.foodie.common.model.Table
import app.kserno.foodie.common.model.User
import app.kserno.foodie.common.model.toUser
import com.parse.Parse
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.io.File


/**
 *  Created by filipsollar on 2019-04-10
 */
class ParseApi(context: Context): Api {


    override fun cancelOrder() {
        currentOrder.clear()
        newOrderSubject.onNext(currentOrder)
    }


    override fun addFoodToOrder(food: Food) {
        var added = false
        currentOrder.forEach {
            if (it.food.id == food.id) {
                it.count += 1
                added = true
            }
        }
        if (!added) {
            currentOrder.add(FoodOrderModel(food, 1))
        }
        newOrderSubject.onNext(currentOrder)
    }

    private val currentOrder: MutableList<FoodOrderModel> = mutableListOf()

    private val newOrderSubject = BehaviorSubject.create<List<FoodOrderModel>>()

    init {
        newOrderSubject.onNext(currentOrder)
    }

    override val newOrder: Observable<List<FoodOrderModel>>
        get() = newOrderSubject



    override fun addFood(name: String, description: String, price: Double, categoryId: String, image: File): Completable {
        return Completable.create {
            val file = ParseFile(image.name, image.readBytes())
            file.saveInBackground { e: ParseException? ->
                if (e != null) {
                    it.onError(e)
                    return@saveInBackground
                }
                val food = ParseObject("Food")
                food.put("name", name)
                food.put("description", description)
                food.put("price", price)
                food.put("categoryId", categoryId)
                food.put("photoUrl", file.url)
                food.put("restaurantId", getCurrentUser()!!.restaurantId)
                food.saveInBackground { ex ->
                    if (ex != null) {
                        it.onError(ex)
                        return@saveInBackground
                    }
                    it.onComplete()
                }
            }

        }

    }

    override fun addAccount(name: String, username: String, password: String): Completable {
        return Completable.create {
            val user = ParseUser()
            user.username = username
            user.setPassword(password)
            user.put("name", name)
            user.put("restaurantId", getCurrentUser()!!.restaurantId)
            user.signUpInBackground{ e ->
                if (e != null) {
                    it.onError(e)
                    return@signUpInBackground
                }
                it.onComplete()
            }
        }
    }

    override fun addTable(name: String, beaconId: String): Completable {
        return Completable.create {
            val parseTable = ParseObject("Table")
            parseTable.put("name", name)
            parseTable.put("beaconId", beaconId)
            parseTable.put("restaurantId", getCurrentUser()!!.restaurantId)
            parseTable.saveInBackground { e ->
                if (e != null) {
                    it.onError(e)
                    return@saveInBackground
                }
                it.onComplete()
            }
        }
    }

    override fun updateTable(table: Table): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOrderByBeacon(beaconId: String): Observable<Order> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsers(restaurantId: String): Single<List<User>> {
        return Single.create {
            val query = ParseUser.getQuery()
            query.whereEqualTo("restaurantId", restaurantId)
            query.findInBackground { objects, e ->
                if (e != null) {
                    it.onError(e)
                    return@findInBackground
                }
                it.onSuccess(objects.map { User.fromParseObject(it) })
            }
        }
    }

    override fun getTables(restaurantId: String): Single<List<Table>> {
        return Single.create {
            val query = ParseQuery.getQuery<ParseObject>("Table")
            query.whereEqualTo("restaurantId", restaurantId)
            query.findInBackground { objects, e ->
                if (e != null) {
                    it.onError(e)
                    return@findInBackground
                }
                it.onSuccess(objects.map { Table.fromParseObject(it) })
            }
        }
    }

    override fun login(username: String, password: String): Single<User> {
        return Single.create {
            ParseUser.logInInBackground(username, password) { user, e ->
                if (e != null) {
                    it.onError(e)
                    return@logInInBackground
                }
                it.onSuccess(user.toUser())
            }
        }
    }

    override fun getFoods(restaurantId: String, categoryId: String): Single<List<Food>> {
        return Single.create {
            val query = ParseQuery.getQuery<ParseObject>("Food")
            query.whereEqualTo("restaurantId", restaurantId)
            query.whereEqualTo("categoryId", categoryId)
            query.findInBackground { objects, e ->
                if (e != null) {
                    it.onError(e)
                    return@findInBackground
                }
                it.onSuccess(objects.map { Food.fromParseObject(it)  })
            }

        }
    }

    override fun getFoodCategories(): Single<List<FoodCategory>> {
        return Single.create {
            val query = ParseQuery.getQuery<ParseObject>("Category")
            //query.whereEqualTo("restaurantId", restaurantId) TODO filter
            query.findInBackground { objects, e ->
                if (e != null) {
                    it.onError(e)
                    return@findInBackground
                }
                it.onSuccess(objects.map { FoodCategory.fromParseObject(it) })
            }
        }

    }

    override fun getCurrentUser(): User? {
        if (ParseUser.getCurrentUser() == null) {
            return null
        }
        return ParseUser.getCurrentUser().toUser()
    }

    override fun signOut(): Completable {
        return Completable.create { emitter ->
            ParseUser.logOutInBackground {
                if (it != null) {
                    emitter.onError(it)
                } else {
                    emitter.onComplete()
                }
            }
        }
    }

    init {
        Parse.initialize(Parse.Configuration.Builder(context)
                .applicationId("MdORTUFMU4Nt8p73kEblijP76WNSlOGsgr3hKzkr")
                // if defined
                .clientKey("Ex6RcLRtXvSsh5Ac885NhsCgu96Gkn4nrF616uTH")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }



}