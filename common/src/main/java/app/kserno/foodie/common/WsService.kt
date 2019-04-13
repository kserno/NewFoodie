package app.kserno.foodie.common

import android.content.Context
import android.util.Log
import app.kserno.foodie.common.model.FoodOrder
import app.kserno.foodie.common.model.Order
import app.kserno.foodie.common.model.WsRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import com.squareup.moshi.Types.newParameterizedType



/**
 *  Created by filipsollar on 2019-04-12
 */
class WsService(private val moshi: Moshi,
private val context:Context) {

    private var socket : Socket? = null

    init {
        try {
            socket = IO.socket("https://silent-chaos-server.herokuapp.com/")
            socket!!.on("output") {
                Log.d("ws", it.toString())
                val obj = it[0] as JSONObject
                val order = moshi.adapter<Order>(Order::class.java).fromJson(obj.toString())
                subject.onNext(order!!)
            }

            socket!!.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val subject = BehaviorSubject.create<Order>()


    fun getObservable(): Observable<Order> = subject

    fun startOrder() {
        socket!!.emit(WsRequest.Action.START.name, null)
    }

    fun closeOrder() {
        socket!!.emit(WsRequest.Action.CLOSE.name, null)
    }

    fun startPaying() {
        socket!!.emit(WsRequest.Action.START_PAY.name, null)
    }

    fun closePaying() {
        socket!!.emit(WsRequest.Action.CLOSE_PAY.name, null)
    }

    fun order(order: List<FoodOrder>?) {
        order?.forEach { it.orderedBy = Utils.getUniqueId(context)}
        val data = moshi.adapter<List<FoodOrder>>(Types.newParameterizedType(List::class.java, FoodOrder::class.java)).toJson(order)
        socket!!.emit(WsRequest.Action.ORDER.name, data)
    }

    fun pay(meals:List<FoodOrder>, by: String) {
        meals.forEach {
            it.paidBy = by
        }
        val data = moshi.adapter<List<FoodOrder>>(Types.newParameterizedType(List::class.java, FoodOrder::class.java)).toJson(meals)
        socket!!.emit(WsRequest.Action.PAY.name, data)
    }


}