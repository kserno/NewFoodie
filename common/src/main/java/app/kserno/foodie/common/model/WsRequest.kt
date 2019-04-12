package app.kserno.foodie.common.model

/**
 *  Created by filipsollar on 2019-04-12
 */
data class WsRequest(
        val action: Action,
        val data: List<FoodOrder>? = null
) {

    enum class Action {
        START, ORDER, START_PAY, PAY, CLOSE_PAY, CLOSE
    }
}