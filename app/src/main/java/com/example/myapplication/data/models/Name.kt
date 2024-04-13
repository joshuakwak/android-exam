package com.example.myapplication.data.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Name(
    val title: String,
    val first: String,
    val last: String
) : Parcelable