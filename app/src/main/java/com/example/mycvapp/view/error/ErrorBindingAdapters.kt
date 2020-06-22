package com.example.mycvapp.view.error

import androidx.databinding.BindingAdapter
import com.example.mycvapp.view.error.ErrorConfiguration.BROKEN_DATA
import com.example.mycvapp.view.error.ErrorConfiguration.NETWORK
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import com.example.mycvapp.view.error.ErrorState.NO_ERROR

@BindingAdapter("errorState")
fun ErrorView.setErrorState(errorState: ErrorState) {
    when (errorState) {
        NETWORK_ERROR -> {
            showFullScreen(NETWORK)
        }
        DATA_ERROR -> {
            showFullScreen(BROKEN_DATA)
        }
        NO_ERROR -> {
            hide()
        }
    }
}