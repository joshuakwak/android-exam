package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Response
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.domain.repository.CacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPersonListCacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {
    operator fun invoke(): Flow<Response<List<PersonDetailsEntity>?>> = flow {
        try {
            val data = cacheRepository.getPersonList()
            emit(Response.Success(data))
        } catch (e: IOException) {
            emit(Response.Error("fetch Failed"))
        }
    }
}