package com.kaushal.sqliteapplication

data class ProductDetails(
    var productName : String,
    var price : Int
){
    var productId : Int? = null

    override fun toString(): String {
        return "ProductDetails(productID=$productId,productName=$productName,price=$price)"
    }
}