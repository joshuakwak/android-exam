package com.example.myapplication.common.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object StringExtensions {

    fun String.toStandardDate(format: String = "MMMM dd, yyyy"): String {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")

        val utcDate = utcFormat.parse(this)
        val outputFormat = SimpleDateFormat(format, Locale.getDefault())
        return outputFormat.format(utcDate)
    }
}