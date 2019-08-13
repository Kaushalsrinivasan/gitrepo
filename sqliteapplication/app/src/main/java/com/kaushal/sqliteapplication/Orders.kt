package com.kaushal.sqliteapplication

data class Orders(var customerId : Int, var orderDate : String, var orderAmount : Int) {

    var orderId : Int? = null

    override fun toString(): String {

        return "Orders(orderId=$orderId,customerId=$customerId,orderDate=$orderDate,orderAmount=$orderAmount)"
    }
}