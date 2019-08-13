package com.kaushal.sqliteapplication

import java.text.SimpleDateFormat
import java.util.*

class Parser {

    fun stringToDate(string: String): String {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault()
        )
        val date = Date(string)
        return dateFormat.format(date)
    }
}