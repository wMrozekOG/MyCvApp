package com.example.mycvapp.extensions

import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mycvapp.R
import com.example.mycvapp.view.error.ErrorConfiguration.NETWORK
import com.example.mycvapp.view.error.ErrorConfiguration.BROKEN_DATA
import com.example.mycvapp.view.error.ErrorState
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import com.example.mycvapp.view.error.ErrorState.NO_ERROR
import com.example.mycvapp.view.error.ErrorView

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.setOnRefreshListener(onRefresh: () -> Unit) {
    setOnRefreshListener { onRefresh() }
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.setIsRefreshing(isRequestInProgress: Boolean) {
    isRefreshing = isRequestInProgress
}

@BindingAdapter("visible")
fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * Set 'Not available' if given String is either null or empty
 */
@BindingAdapter("textOrNa")
internal fun TextView.setTextOrNa(string: String?) {
    text = if (string.isNullOrEmpty()) resources.getString(R.string.not_available) else string
}

/**
 * Set text consisting of given Strings and set second's string StyleSpan to BOLD.
 */
fun TextView.formatDefaultBold(text1: String, text2: String, addSpace: Boolean = true) {
    text = SpannableStringBuilder()
        .append(text1)
        .append(if (addSpace) " " else "")
        .bold { append(text2) }
}
