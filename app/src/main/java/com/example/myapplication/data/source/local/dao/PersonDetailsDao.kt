package com.example.myapplication.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.common.Constants.PERSON_LIST_TABLE_NAME
import com.example.myapplication.common.base.BaseDao
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity

@Dao
interface PersonDetailsDao : BaseDao<PersonDetailsEntity> {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun all(): List<PersonDetailsEntity>?

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun get(id: Int): PersonDetailsEntity?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC LIMIT 1")
    suspend fun getFirst(): PersonDetailsEntity?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun nukeTable()

    companion object {
        const val TABLE_NAME = PERSON_LIST_TABLE_NAME
    }
}