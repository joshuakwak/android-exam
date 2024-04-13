package com.example.myapplication.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.source.local.dao.PersonDetailsDao
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity


private const val VERSION_NUMBER = 1

@Database(
    entities = [PersonDetailsEntity::class],
    version = VERSION_NUMBER
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDetailsDao(): PersonDetailsDao
}