package com.kaushal.sqliteapplication

import android.content.Context

class Handler(val context: Context) {

    fun addProduct(productDetails: ProductDetails) {

        val dbHandler = DbHandler(context)

        dbHandler.createNewProduct(productDetails)
    }
    fun addCustomer(customerDetails: CustomerDetails) {

        val dbHandler = DbHandler(context)

        dbHandler.createNewCustomer(customerDetails)
    }
    fun addOrder(orders: Orders) {

        val dbHandler = DbHandler(context)

        dbHandler.createNewOrder(orders)
    }
    fun addOrderDetails(orderDetails: OrderDetails) {

        val dbHandler = DbHandler(context)

        dbHandler.addOrderDetails(orderDetails)
    }

    fun getAllProducts() : ArrayList<ProductDetails>{

        val dbHandler = DbHandler(context)

        return dbHandler.getAllProducts()

    }
    fun getAllCustomers() : ArrayList<CustomerDetails>{

        val dbHandler = DbHandler(context)

        return dbHandler.getAllCustomer()

    }
    fun getAllOrders() : ArrayList<Orders>{

        val dbHandler = DbHandler(context)

        return dbHandler.getAllOrders()

    }

    fun getOrderDetails() : ArrayList<OrderDetails>{

        val dbHandler = DbHandler(context)

        return dbHandler.getOrderDetails()

    }

    fun getSales() : ArrayList<Sales>{

        val dbHandler = DbHandler(context)

        return dbHandler.getSales()

    }

    fun getUnsoldProducts() : ArrayList<String>{

        val dbHandler = DbHandler(context)

        return dbHandler.getUnsoldProducts()

    }

    fun getTopCustomers() : ArrayList<TopCustomers>{

        val dbHandler = DbHandler(context)

        return dbHandler.getTopCustomers()

    }
}