package com.kaushal.sqliteapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sales.*

class SalesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        salestable.setShrinkAllColumns(true)
//        salestable.setStretchAllColumns(true)



        val trHead : TableRow = TableRow(this)
        trHead.id = 0
        trHead.setBackgroundColor(Color.GRAY)


        val textOrderId : TextView = TextView(this)
        textOrderId.setTextColor(Color.WHITE)
        textOrderId.setPadding(5,5,5,5)
        textOrderId.text = "ORDER ID"
        trHead.addView(textOrderId)

        val textCustomerName = TextView(this)
        textCustomerName.setTextColor(Color.WHITE)
        textCustomerName.setPadding(5,5,5,5)
        textCustomerName.text = "CUSTOMER NAME"
        trHead.addView(textCustomerName)

        val textProductName = TextView(this)
        textProductName.setTextColor(Color.WHITE)
        textProductName.setPadding(5,5,5,5)
        textProductName.text = "PRODUCT NAME"
        trHead.addView(textProductName)

        val textQuantity = TextView(this)
        textQuantity.setTextColor(Color.WHITE)
        textQuantity.setPadding(5,5,5,5)
        textQuantity.text = "QUANTITY"
        trHead.addView(textQuantity)

        val textAmount = TextView(this)
        textAmount.setTextColor(Color.WHITE)
        textAmount.setPadding(5,5,5,5)
        textAmount.text = "AMOUNT"
        trHead.addView(textAmount)

        salestable.addView(trHead)

        val sales = intent.getSerializableExtra("sales") as ArrayList<Sales>

        for (index in 0 until sales.size){
            salestable.addView(createNewRow(sales[index]))
        }



    }

    fun createNewRow(item : Sales) : TableRow{
        val tableRow = TableRow(this)
        tableRow.addView(createNewColumn(item.orderId.toString()))
        tableRow.addView(createNewColumn(item.customerName))
        tableRow.addView(createNewColumn(item.productName))
        tableRow.addView(createNewColumn(item.quantity.toString()))
        tableRow.addView(createNewColumn(item.amount.toString()))

        return tableRow

    }

    fun createNewColumn(data : String) : TextView{
        val textview = TextView(this)
        textview.setPadding(5,0,5,0)
        textview.text = data

        return textview
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
