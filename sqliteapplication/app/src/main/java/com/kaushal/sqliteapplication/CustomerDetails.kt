package com.kaushal.sqliteapplication

data class CustomerDetails(var customerName : String) {

    var customerId : Int? = null
    override fun toString(): String {
        return "CustomerDetails(customerId=$customerId,customerName=$customerName)"
    }
}