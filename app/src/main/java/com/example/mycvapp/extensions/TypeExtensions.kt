package com.example.mycvapp.extensions

import android.content.Context
import com.example.mycvapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val EMPTY_STRING = ""

/**
 * Converts given 'Long' to 'String' containing date in format 'dd/MM/yyyy'
 */
fun Long.toFormattedDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return formatter.format(calendar.time)
}

/**
 * Perform 'action' if given String is neither null or empty
 *
 * @param action to be performed
 */
fun String?.doIfNotNullOrEmpty(action: (String) -> Unit) {
    if (!isNullOrEmpty()) action.invoke(this!!)
}

/**
 * Returns "" if given String is null
 */
val String?.orEmpty: String
    get() = this ?: ""

/**
 * Returns "Not available" if given String is either null or empty
 */
fun String?.orNotAvailable(context: Context): String =
    if (isNullOrEmpty()) context.resources.getString(R.string.not_available) else this!!