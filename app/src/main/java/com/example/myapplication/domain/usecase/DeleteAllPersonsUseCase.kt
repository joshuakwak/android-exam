package com.example.myapplication.domain.usecase

import com.example.myapplication.common.Response
import com.example.myapplication.domain.repository.CacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteAllPersonsUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {
    operator fun invoke(): Flow<Response<Unit>> = flow {
        try {
            val data = cacheRepository.deleteAllPersons()
            emit(Response.Success(data))
        } catch (e: IOException) {
            emit(Response.Error("deletion Failed"))
        }
    }
}