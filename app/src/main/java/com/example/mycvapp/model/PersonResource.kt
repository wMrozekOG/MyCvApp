package com.example.mycvapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycvapp.database.Person
import com.example.mycvapp.database.PersonDatabase
import com.example.mycvapp.extensions.isNetworkException
import com.example.mycvapp.model.RequestState.Error
import com.example.mycvapp.model.RequestState.Loading
import com.example.mycvapp.model.RequestState.Success
import com.example.mycvapp.view.error.ErrorState
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonResource constructor(
    private val api: Api,
    private val database: PersonDatabase
) {

    val source: LiveData<Person> = database.personDao().get()

    private val _requestState = MutableLiveData<RequestState>().apply { value = Loading }
    val requestState: LiveData<RequestState> = _requestState

    suspend fun get() = withContext(Dispatchers.IO){
        _requestState.postValue(Loading)
        try {
            withContext(Dispatchers.Default) { database.personDao().put(api.getPersonalData()) }
            _requestState.postValue(Success)
        } catch (exception: Exception) {
            _requestState.postValue(
                Error(
                    if (exception.isNetworkException()) {
                        NETWORK_ERROR
                    } else {
                        DATA_ERROR
                    }
                )
            )
        }
    }
}

sealed class RequestState {
    object Success : RequestState()
    object Loading : RequestState()
    data class Error(val errorState: ErrorState) : RequestState() {
        fun isNetworkError() = errorState == NETWORK_ERROR
        fun isDataError() = errorState == DATA_ERROR
    }
}