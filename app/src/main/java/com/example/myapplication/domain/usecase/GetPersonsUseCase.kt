package com.example.myapplication.domain.usecase

import com.example.myapplication.data.source.remote.response.PersonsResponse
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.common.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPersonsUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(results: Int): Flow<Response<PersonsResponse>> = flow {
        try {
            emit(Response.Loading())
            val data = repository.getRandomPersons(results)
            emit(Response.Success(data))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}