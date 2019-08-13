package com.kaushal.sqliteapplication

import java.io.Serializable

data class TopCustomers(val customerName : String, val billAmount: Int) : Serializable