package com.example.myapplication.data.repository

import com.example.myapplication.data.source.local.database.AppDatabase
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.domain.repository.CacheRepository

class CacheRepositoryImpl(
    private val database: AppDatabase,
) : CacheRepository {
    override suspend fun getPersonList(): List<PersonDetailsEntity>? {
        return database.personDetailsDao().all()
    }

    override suspend fun addPerson(personDetailsEntity: PersonDetailsEntity): Long {
        return database.personDetailsDao().insert(personDetailsEntity)
    }

    override suspend fun deleteAllPersons() {
        return database.personDetailsDao().nukeTable()
    }

}