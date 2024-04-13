package com.example.myapplication.domain.repository

import com.example.myapplication.data.source.local.entity.PersonDetailsEntity

interface CacheRepository {
    suspend fun getPersonList(): List<PersonDetailsEntity>?
    suspend fun addPerson(personDetailsEntity: PersonDetailsEntity): Long
    suspend fun deleteAllPersons()
}