package com.kaushal.sqliteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var handler = Handler(applicationContext)

        addproduct.isEnabled = false
        addcustomer.isEnabled = false
        addorders.isEnabled = false
        addorderdetails.isEnabled = false

        addproduct.setOnClickListener {
            val productName = "box"
            val price = 20

            val productDetails = ProductDetails(productName,price)

            handler.addProduct(productDetails)
        }

        addcustomer.setOnClickListener {

            val customerName = "vignesh"

            val customerDetails = CustomerDetails(customerName)

            handler.addCustomer(customerDetails)

        }

        addorders.setOnClickListener {

            val customerId = 2
            val orderDate = "2019-07-31"
            val orderAmount = 30

            val orders = Orders(customerId,orderDate,orderAmount)

            handler.addOrder(orders)

        }

        addorderdetails.setOnClickListener {

            val orderId = 2
            val productId = 4
            val quantity = 2
            val amount = 4

            val orderDetails = OrderDetails(orderId,productId,quantity,amount)

            handler.addOrderDetails(orderDetails)
        }

        getallproducts.setOnClickListener {

            textView.text = handler.getAllProducts().toString()

        }

        getallcustomers.setOnClickListener {

            textView.text = handler.getAllCustomers().toString()

        }

        getallorders.setOnClickListener {

            textView.text = handler.getAllOrders().toString()

        }

        getorderdetails.setOnClickListener {

            textView.text = handler.getOrderDetails().toString()

        }

        getsales.setOnClickListener {

//            textView.text = handler.getSales()

            val intent = Intent(this,SalesActivity::class.java)
            intent.putExtra("sales", handler.getSales())
            startActivity(intent)

        }

        getunsoldproducts.setOnClickListener {

//            textView.text = handler.getUnsoldProducts()

            val intent = Intent(this,UnsoldProductsActivity::class.java)
            intent.putExtra("unsold", handler.getUnsoldProducts())
            startActivity(intent)
        }

        gettopcustomers.setOnClickListener {


//            textView.text = handler.getTopCustomers()

            val intent = Intent(this,TopCustomersActivity::class.java)
            intent.putExtra("topcustomers", handler.getTopCustomers())
            startActivity(intent)

//            DbHandler(this,null,null,1).delete()
        }

    }
}
