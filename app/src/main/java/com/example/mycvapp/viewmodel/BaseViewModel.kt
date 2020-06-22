package com.example.mycvapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    fun launch(blockScope: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(block = blockScope)
}