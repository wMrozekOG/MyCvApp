package com.example.mycvapp.view.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.example.mycvapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.error_view.view.errorIcon
import kotlinx.android.synthetic.main.error_view.view.errorMessage
import kotlinx.android.synthetic.main.error_view.view.errorTitle

class ErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
: FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.error_view, this)
    }

    fun showFullScreen(errorConfiguration: ErrorConfiguration) {
        visibility = View.VISIBLE
        errorIcon.setImageDrawable(resources.getDrawable(errorConfiguration.imageResourceId))
        errorTitle.text = resources.getString(errorConfiguration.titleResourceId)
        errorMessage.text = resources.getString(errorConfiguration.descriptionResourceId)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun showSnackbar(errorConfiguration: ErrorConfiguration) {
        val message = resources.getString(errorConfiguration.titleResourceId) + " " +
                resources.getString(errorConfiguration.descriptionResourceId) + " " +
                resources.getString(R.string.cached_data_loaded)

        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }
}