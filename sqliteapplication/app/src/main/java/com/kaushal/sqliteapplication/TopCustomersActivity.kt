package com.kaushal.sqliteapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_top_customers.*

class TopCustomersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_customers)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val trHead : TableRow = TableRow(this)
        trHead.id = 0
        trHead.setBackgroundColor(Color.GRAY)


        val textCustomerName : TextView = TextView(this)
        textCustomerName.setTextColor(Color.WHITE)
        textCustomerName.setPadding(5,5,5,5)
        textCustomerName.text = "CUSTOMER NAME"
        trHead.addView(textCustomerName)

        val textBillAmount : TextView = TextView(this)
        textBillAmount.setTextColor(Color.WHITE)
        textBillAmount.setPadding(5,5,5,5)
        textBillAmount.text = "ORDER AMOUNT"
        trHead.addView(textBillAmount)

        topcustomerstable.addView(trHead)

        val unsold = intent.getSerializableExtra("topcustomers") as ArrayList<TopCustomers>

        for (index in 0 until unsold.size){
            topcustomerstable.addView(createNewRow(unsold[index]))
        }
    }

    fun createNewRow(item : TopCustomers) : TableRow{

        val tableRow = TableRow(this)
        tableRow.addView(createNewColumn(item.customerName))
        tableRow.addView(createNewColumn(item.billAmount.toString()))

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
