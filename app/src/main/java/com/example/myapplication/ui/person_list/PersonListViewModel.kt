package com.example.myapplication.ui.person_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Constants.DEFAULT_NUMBER_OF_PERSONS
import com.example.myapplication.common.Response
import com.example.myapplication.common.base.BaseViewModel
import com.example.myapplication.data.source.local.entity.PersonDetailsEntity
import com.example.myapplication.data.source.remote.response.PersonItemResponse
import com.example.myapplication.data.source.remote.response.PersonsResponse
import com.example.myapplication.domain.usecase.AddPersonCacheUseCase
import com.example.myapplication.domain.usecase.DeleteAllPersonsUseCase
import com.example.myapplication.domain.usecase.GetPersonListCacheUseCase
import com.example.myapplication.domain.usecase.GetPersonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val addPersonCacheUseCase: AddPersonCacheUseCase,
    private val getPersonListCacheUseCase: GetPersonListCacheUseCase,
    private val deleteAllPersonsUseCase: DeleteAllPersonsUseCase,
): BaseViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _listItems = MutableStateFlow(listOf<PersonDetailsEntity>())
    val listItems = _listItems.asStateFlow()

    init {
        getPersonListCache()
    }

    fun getPersonList() {
        getPersonsUseCase(DEFAULT_NUMBER_OF_PERSONS).onEach { result ->
            when (result) {
                is Response.Success -> {
                    deletePersonList()
                    setPersonList(result.data?.results ?: listOf())
                    _isLoading.value = false
                }
                is Response.Error -> {
                    _isLoading.value = false
                }
                is Response.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPersonListCache() {
        getPersonListCacheUseCase().onEach { response ->
            when (response) {
                is Response.Success -> {
                    Timber.d(response.data.toString())
                    _listItems.value = response.data ?: listOf()
                    _isLoading.value = false
                }
                is Response.Error -> {
                    getPersonList()
                    _isLoading.value = false
                }
                is Response.Loading -> {
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deletePersonList() {
        deleteAllPersonsUseCase().launchIn(viewModelScope)
        Timber.d("Deleted")
    }

    private fun setPersonList(list: List<PersonItemResponse>) {
        list.forEach {
            addPersonCacheUseCase(
                PersonDetailsEntity(
                    name = "${ it.name?.first } ${it.name?.last}",
                    gender = it.gender,
                    location = "${it.location?.city}, " +
                            "${it.location?.state}, " +
                            "${it.location?.country}",
                    email = it.email,
                    age = it.dob?.age,
                    dob = it.dob?.date,
                    phone = it.phone,
                    picture = it.picture?.large,
                    nat = it.nat
                )
            ).onEach { response ->
                Timber.tag("Person ID").d(response.data.toString())
                getPersonListCache()
            }.launchIn(viewModelScope)
        }
    }

}