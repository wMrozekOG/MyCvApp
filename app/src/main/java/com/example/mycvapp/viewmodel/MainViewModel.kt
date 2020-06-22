package com.example.mycvapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mycvapp.database.Person
import com.example.mycvapp.database.WorkHistoryEntry
import com.example.mycvapp.extensions.EMPTY_STRING
import com.example.mycvapp.model.PersonRepository
import com.example.mycvapp.model.PersonalData
import com.example.mycvapp.model.RequestState
import com.example.mycvapp.model.RequestState.Error
import com.example.mycvapp.model.RequestState.Loading
import com.example.mycvapp.model.SingleLiveEvent
import com.example.mycvapp.model.SortOrder
import com.example.mycvapp.view.error.ErrorState
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import com.example.mycvapp.view.error.ErrorState.NO_ERROR
import com.example.mycvapp.model.SortOrder.LATEST_FIRST
import com.example.mycvapp.model.SortOrder.LATEST_LAST

class MainViewModel(
    private val personRepository: PersonRepository
) : BaseViewModel() {

    private var dataHash = -1

    private val sortOrder = MutableLiveData<SortOrder>(LATEST_FIRST)

    private val source: LiveData<Person> = Transformations.map(personRepository.source) {
        it?.run {
            dataHash = it.hashCode()
            _isDataVisible.value = true
            changeErrorState(requestState.value!!)
        }
        it
    }

    private val requestState = Transformations.map(personRepository.requestState) {
        changeErrorState(it)
        it
    }

    private val _fullScreenErrorState = MutableLiveData<ErrorState>().apply { value = NO_ERROR }
    val fullScreenErrorState: LiveData<ErrorState> = _fullScreenErrorState

    private val _snackbarErrorEvent = SingleLiveEvent<ErrorState>()
    val snackbarErrorEvent = _snackbarErrorEvent

    private val _isDataVisible = MutableLiveData<Boolean>()
    val isDataVisible: LiveData<Boolean> = _isDataVisible

    val personalData: LiveData<PersonalData> = Transformations.map(source) {
        it?.run {
            PersonalData(it.name, it.photo, it.role, it.summary, it.technicalSummary)
        } ?: PersonalData()
    }

    val isRequestInProgress: LiveData<Boolean> = Transformations.map(requestState) {
        it is Loading
    }

    val workHistory = MediatorLiveData<List<WorkHistoryEntry>>().apply {
        addSource(source) { person ->
            value = person?.workHistory?.run {
                if (sortOrder.value == LATEST_FIRST) sortedBy { it.startDate }
                else sortedByDescending { entry -> entry.startDate }
            }
        }
        addSource(sortOrder) { sortOrder ->
            value = value?.run {
                if (sortOrder == LATEST_FIRST) sortedBy { entry -> entry.startDate }
                else sortedByDescending { entry -> entry.startDate }
            }
        }
    }

    val technicalSummary: String
        get() = personalData.value?.technicalSummary ?: EMPTY_STRING

    init {
        refresh()
    }

    fun refresh() {
        launch {
            personRepository.refresh()
        }
    }

    fun sortOrderActionClicked() {
        sortOrder.value = if (sortOrder.value == LATEST_FIRST) LATEST_LAST else LATEST_FIRST
    }

    private fun changeErrorState(requestState: RequestState) {
        requestState.let {
            when {
                it is Error && it.isNetworkError() && dataHash == -1 -> _fullScreenErrorState.value = NETWORK_ERROR
                it is Error && it.isDataError() && dataHash == -1 -> _fullScreenErrorState.value = DATA_ERROR
                it is Error && it.isNetworkError() && dataHash != -1 -> {
                    snackbarErrorEvent.value = NETWORK_ERROR
                    _fullScreenErrorState.value = NO_ERROR
                }
                it is Error && it.isDataError() && dataHash != -1 -> {
                    snackbarErrorEvent.value = DATA_ERROR
                    _fullScreenErrorState.value = NO_ERROR
                }
                else -> _fullScreenErrorState.value = NO_ERROR
            }
        }
    }
}