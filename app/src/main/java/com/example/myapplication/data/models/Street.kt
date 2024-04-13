package com.example.myapplication.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Street(
    val number: Long,
    val name: String
)
