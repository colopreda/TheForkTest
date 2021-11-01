package com.colopreda.theforktest.utils

import java.text.NumberFormat
import java.util.Currency

fun Float.toFormattedEuro(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("EUR")
    return format.format(this)
}