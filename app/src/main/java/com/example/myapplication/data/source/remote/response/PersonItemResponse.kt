package com.example.myapplication.data.source.remote.response

import com.example.myapplication.data.models.DateOfBirth
import com.example.myapplication.data.models.Location
import com.example.myapplication.data.models.Name
import com.example.myapplication.data.models.Picture
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonItemResponse(
    val gender: String? = null,
    val name: Name? = null,
    val location: Location? = null,
    val email: String? = null,
    val dob: DateOfBirth? = null,
    val phone: String? = null,
    val picture: Picture? = null,
    val nat: String? = null,
)