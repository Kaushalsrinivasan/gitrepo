package com.kaushal.sqliteapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_PRODUCTDETAILS_TABLE = ("CREATE TABLE $TABLE_PRODUCT_DETAILS($COLUMN_PRODUCTID INTEGER PRIMARY KEY,$COLUMN_PRODUCTNAME TEXT,$COLUMN_PRICE INTEGER)")
        db.execSQL(CREATE_PRODUCTDETAILS_TABLE)

        val CREATE_CUSTOMERDETAILS_TABLE = ("CREATE TABLE $TABLE_CUSTOMER_DETAILS($COLUMN_CUSTOMERID INTEGER PRIMARY KEY,$COLUMN_CUSTOMERNAME TEXT)")
        db.execSQL(CREATE_CUSTOMERDETAILS_TABLE)

        val CREATE_ORDERS_TABLE = ("CREATE TABLE $TABLE_ORDERS($COLUMN_ORDERID INTEGER PRIMARY KEY,$COLUMN_CUSTOMERID INTEGER,$COLUMN_ORDERDATE DATE, $COLUMN_ORDERAMOUNT INTEGER, CONSTRAINT CUSTOMERID  FOREIGN KEY ( $COLUMN_CUSTOMERID ) REFERENCES $TABLE_CUSTOMER_DETAILS( $COLUMN_CUSTOMERID ))")
        db.execSQL(CREATE_ORDERS_TABLE)

        val CREATE_ORDERDETAILS_TABLE = ("CREATE TABLE $TABLE_ORDER_DETAILS($COLUMN_ORDERID INTEGER, $COLUMN_PRODUCTID INTEGER, $COLUMN_QUANTITY INTEGER, $COLUMN_AMOUNT INTEGER, PRIMARY KEY ($COLUMN_ORDERID,$COLUMN_PRODUCTID), CONSTRAINT ORDERID FOREIGN KEY ( $COLUMN_ORDERID ) REFERENCES $TABLE_ORDERS ( $COLUMN_ORDERID ), CONSTRAINT PRODUCTID FOREIGN KEY ( $COLUMN_PRODUCTID ) REFERENCES $TABLE_PRODUCT_DETAILS ( $COLUMN_PRODUCTID ))")
        db.execSQL(CREATE_ORDERDETAILS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT_DETAILS")
        onCreate(db)

    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Traders.db"

        val TABLE_PRODUCT_DETAILS = "ProductDetails"
        val COLUMN_PRODUCTID = "productId"
        val COLUMN_PRODUCTNAME = "productName"
        val COLUMN_PRICE = "price"

        val TABLE_CUSTOMER_DETAILS = "CustomerDetails"
        val COLUMN_CUSTOMERID = "customerId"
        val COLUMN_CUSTOMERNAME = "customerName"

        val TABLE_ORDERS = "Orders"
        val COLUMN_ORDERID = "orderId"
        val COLUMN_ORDERDATE = "orderDate"
        val COLUMN_ORDERAMOUNT = "orderAmount"

        val TABLE_ORDER_DETAILS = "OrderDetails"
        val COLUMN_QUANTITY = "quantity"
        val COLUMN_AMOUNT = "amount"
    }

    fun createNewProduct(productDetails: ProductDetails) {

        val values = ContentValues()
        values.put(COLUMN_PRODUCTNAME, productDetails.productName)
        values.put(COLUMN_PRICE, productDetails.price)

        val db = this.writableDatabase

        db.insert(TABLE_PRODUCT_DETAILS, null, values)
        db.close()

    }

    fun createNewCustomer(customerDetails: CustomerDetails) {

        val values = ContentValues()
        values.put(COLUMN_CUSTOMERNAME, customerDetails.customerName)

        val db = this.writableDatabase

        db.insert(TABLE_CUSTOMER_DETAILS, null, values)
        db.close()

    }

    fun createNewOrder(orders: Orders) {

        val values = ContentValues()
        values.put(COLUMN_CUSTOMERID, orders.customerId)
        values.put(COLUMN_ORDERDATE, orders.orderDate)
        values.put(COLUMN_ORDERAMOUNT, orders.orderAmount)

        val db = this.writableDatabase

        db.insert(TABLE_ORDERS, null, values)
        db.close()

    }

    fun addOrderDetails(orderDetails: OrderDetails) {

        val values = ContentValues()
        values.put(COLUMN_ORDERID, orderDetails.orderId)
        values.put(COLUMN_PRODUCTID, orderDetails.productId)
        values.put(COLUMN_QUANTITY, orderDetails.quantity)
        values.put(COLUMN_AMOUNT, orderDetails.amount)

        val db = this.writableDatabase

        db.insert(TABLE_ORDER_DETAILS, null, values)
        db.close()

    }

    fun getAllProducts() : ArrayList<ProductDetails>{

        val productsList = ArrayList<ProductDetails>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_PRODUCT_DETAILS"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCTID))
                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME))
                val price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE))

                val products = ProductDetails(productName,price)
                products.productId = productId

                productsList.add(products)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return productsList

    }

    fun getAllCustomer() : ArrayList<CustomerDetails>{

        val customersList = ArrayList<CustomerDetails>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_CUSTOMER_DETAILS"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val customerId = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                val customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))

                val customer = CustomerDetails(customerName)
                customer.customerId = customerId

                customersList.add(customer)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return customersList

    }

    fun getAllOrders() : ArrayList<Orders>{

        val orderList = ArrayList<Orders>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_ORDERS"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val orderId = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDERID))
                val customerId = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMERID))
                val orderDate = cursor.getString(cursor.getColumnIndex(COLUMN_ORDERDATE))
                val orderAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDERAMOUNT))

                val order = Orders(customerId,orderDate,orderAmount)
                order.orderId = orderId

                orderList.add(order)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return orderList

    }
    fun getOrderDetails() : ArrayList<OrderDetails>{

        val orderDetailsList = ArrayList<OrderDetails>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_ORDER_DETAILS"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val orderId = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDERID))
                val productId = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCTID))
                val quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))
                val amount = cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT))

                val orderDetails = OrderDetails(orderId,productId,quantity,amount)

                orderDetailsList.add(orderDetails)
                cursor.moveToNext()
            }
        }
        cursor.close()
        return orderDetailsList

    }

    fun getSales() : ArrayList<Sales> {

        val SELECT_SALES = "select product.$COLUMN_ORDERID as $COLUMN_ORDERID, $COLUMN_CUSTOMERNAME, $COLUMN_PRODUCTNAME, $COLUMN_QUANTITY, $TABLE_ORDER_DETAILS.$COLUMN_AMOUNT as $COLUMN_AMOUNT from ($TABLE_PRODUCT_DETAILS join $TABLE_ORDER_DETAILS using ($COLUMN_PRODUCTID)) as product join ($TABLE_CUSTOMER_DETAILS join $TABLE_ORDERS using ($COLUMN_CUSTOMERID)) using ($COLUMN_ORDERID) where $TABLE_ORDERS.$COLUMN_ORDERDATE >= '2019-07-31' and $TABLE_ORDERS.$COLUMN_ORDERDATE <= '2019-07-31'"

//        val sales = StringBuilder()
//        sales.append("[")
        val sales = ArrayList<Sales>()
        val db = writableDatabase
        val selectQuery = SELECT_SALES
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val orderId = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDERID))
                val customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))
                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME))
                val quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY))
                val price = cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT))
                cursor.moveToNext()
                val item = Sales(orderId,customerName,productName,quantity,price)
                sales.add(item)

//                sales.append("{orderID=$orderId,customerName=$customerName,productName=$productName,quantity=$quantity,price=$price}")
//                if(!cursor.isAfterLast){
//                    sales.append(",")
//                }
            }
        }
        cursor.close()
//        sales.append("]")
        return sales

    }

    fun getUnsoldProducts() : ArrayList<String> {

        val SELECT_SALES = "select $COLUMN_PRODUCTNAME from $TABLE_PRODUCT_DETAILS where not exists (select $COLUMN_PRODUCTID from $TABLE_ORDER_DETAILS join $TABLE_ORDERS using ($COLUMN_ORDERID) where $COLUMN_ORDERDATE >= '2019-07-31' and $COLUMN_ORDERDATE <= '2019-07-31' and $TABLE_PRODUCT_DETAILS.$COLUMN_PRODUCTID=$TABLE_ORDER_DETAILS.$COLUMN_PRODUCTID)"

//        val sales = StringBuilder()
//        sales.append("[")

        val unsold = ArrayList<String>()
        val db = writableDatabase
        val selectQuery = SELECT_SALES
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME))
                cursor.moveToNext()
                unsold.add(productName)

//                sales.append("{productName=$productName}")
//                if(!cursor.isAfterLast){
//                    sales.append(",")
//                }


            }
        }
        cursor.close()
//        sales.append("]")
        return unsold

    }

    fun getTopCustomers() : ArrayList<TopCustomers> {



        val SELECT_SALES = "select $COLUMN_CUSTOMERNAME, $COLUMN_ORDERAMOUNT from $TABLE_ORDERS join $TABLE_CUSTOMER_DETAILS using ($COLUMN_CUSTOMERID) where $COLUMN_ORDERDATE >= '2019-07-31' and $COLUMN_ORDERDATE <= '2019-07-31' order by $COLUMN_ORDERAMOUNT desc"

//        val sales = StringBuilder()
//        sales.append("[")

        val topCustomers = ArrayList<TopCustomers>()
        val db = writableDatabase
        val selectQuery = SELECT_SALES
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {

                val customerName = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMERNAME))
                val orderAmount = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDERAMOUNT))
                cursor.moveToNext()
                topCustomers.add(TopCustomers(customerName,orderAmount))
//                sales.append("{customerName=$customerName,orderAmount=$orderAmount}")
//                if(!cursor.isAfterLast){
//                    sales.append(",")
//                }
            }
        }
        cursor.close()
//        sales.append("]")
        return topCustomers

    }

    fun deleteProduct(productId : Int){


        val db = writableDatabase
        db.delete(TABLE_PRODUCT_DETAILS, COLUMN_PRODUCTID + " = ?", arrayOf("$productId"))

    }

    fun deleteCustomer(customerId : Int){


        val db = writableDatabase

        db.delete(TABLE_CUSTOMER_DETAILS, COLUMN_CUSTOMERID + " = ?", arrayOf("$customerId"))

    }

    fun deleteOrders(orderId : Int){


        val db = writableDatabase
        db.delete(TABLE_ORDERS, COLUMN_ORDERID + " = ?", arrayOf("$orderId"))

    }

    fun deleteOrderDetails(orderId: Int, productId: Int){

        val db = writableDatabase
        db.delete(TABLE_ORDER_DETAILS,"$COLUMN_ORDERID = ? AND $COLUMN_PRODUCTID = ?",arrayOf("$orderId","$productId"))
    }






}