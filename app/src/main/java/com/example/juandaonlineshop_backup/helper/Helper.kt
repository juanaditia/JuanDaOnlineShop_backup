package com.example.juandaonlineshop_backup.helper

import java.text.NumberFormat
import java.util.*

class Helper {
    fun gantiRupiah(string: String): String {
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(string))
    }
}