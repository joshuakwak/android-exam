package com.example.myapplication.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.common.Constants.COLUMN_AGE
import com.example.myapplication.common.Constants.COLUMN_DATE_OF_BIRTH
import com.example.myapplication.common.Constants.COLUMN_EMAIL
import com.example.myapplication.common.Constants.COLUMN_GENDER
import com.example.myapplication.common.Constants.COLUMN_ID
import com.example.myapplication.common.Constants.COLUMN_LOCATION
import com.example.myapplication.common.Constants.COLUMN_NAME
import com.example.myapplication.common.Constants.COLUMN_NAT
import com.example.myapplication.common.Constants.COLUMN_PHONE
import com.example.myapplication.common.Constants.COLUMN_PICTURE
import com.example.myapplication.common.Constants.PERSON_LIST_TABLE_NAME
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
@Entity(tableName = PERSON_LIST_TABLE_NAME)
data class PersonDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int? = null,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String? = null,
    @ColumnInfo(name = COLUMN_AGE)
    val age: Int? = null,
    @ColumnInfo(name = COLUMN_GENDER)
    val gender: String? = null,
    @ColumnInfo(name = COLUMN_LOCATION)
    val location: String? = null,
    @ColumnInfo(name = COLUMN_EMAIL)
    val email: String? = null,
    @ColumnInfo(name = COLUMN_DATE_OF_BIRTH)
    val dob: String? = null,
    @ColumnInfo(name = COLUMN_PHONE)
    val phone: String? = null,
    @ColumnInfo(name = COLUMN_PICTURE)
    val picture: String? = null,
    @ColumnInfo(name = COLUMN_NAT)
    val nat: String? = null,
) : Parcelable
