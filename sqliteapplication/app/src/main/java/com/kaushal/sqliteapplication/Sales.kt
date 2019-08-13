package com.kaushal.sqliteapplication

import java.io.Serializable

data class Sales (val orderId : Int, val customerName : String, val productName : String, val quantity : Int, val amount : Int) : Serializable