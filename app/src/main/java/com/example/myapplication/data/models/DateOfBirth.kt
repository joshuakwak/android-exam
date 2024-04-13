package com.example.myapplication.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateOfBirth(
    val date: String,
    val age: Int
)
