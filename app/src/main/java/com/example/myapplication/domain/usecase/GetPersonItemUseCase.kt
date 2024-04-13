package com.example.myapplication.domain.usecase

import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.common.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPersonItemUseCase @Inject constructor(
    private val repository: ApiRepository
) {
    operator fun invoke(): Flow<Response<PersonItemResponse>> = flow {
        try {
            emit(Response.Loading())
            val data = repository.getRandomPerson()
            emit(Response.Success(data))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}