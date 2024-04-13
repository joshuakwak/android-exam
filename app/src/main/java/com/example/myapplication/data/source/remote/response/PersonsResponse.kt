package com.example.myapplication.data.source.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonsResponse(
    val results: List<PersonItemResponse> = listOf()
)
