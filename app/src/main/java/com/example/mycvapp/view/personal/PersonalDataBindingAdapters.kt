package com.example.mycvapp.view.personal

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mycvapp.R
import com.example.mycvapp.extensions.doIfNotNullOrEmpty
import com.squareup.picasso.Picasso

@BindingAdapter("person")
internal fun ImageView.setPersonalImage(photoUrl: String?) {
    photoUrl.doIfNotNullOrEmpty { url ->
        Picasso.get().load(url).placeholder(R.drawable.icon_no_image).into(this)
    }
}