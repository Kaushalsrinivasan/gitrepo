package com.kaushal.sqliteapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_unsold_products.*

class UnsoldProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unsold_products)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        unsoldproductstable.setShrinkAllColumns(true)
//        unsoldproductstable.setStretchAllColumns(true)

        val trHead : TableRow = TableRow(this)
        trHead.id = 0
        trHead.setBackgroundColor(Color.GRAY)


        val textProductName : TextView = TextView(this)
        textProductName.setTextColor(Color.WHITE)
        textProductName.setPadding(5,5,5,5)
        textProductName.text = "PRODUCT NAME"
        trHead.addView(textProductName)

        unsoldproductstable.addView(trHead)

        val unsold = intent.getSerializableExtra("unsold") as ArrayList<String>

        for (index in 0 until unsold.size){
            unsoldproductstable.addView(createNewRow(unsold[index]))
        }
    }

    fun createNewRow(item : String) : TableRow{
        val tableRow = TableRow(this)
        tableRow.addView(createNewColumn(item))

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
