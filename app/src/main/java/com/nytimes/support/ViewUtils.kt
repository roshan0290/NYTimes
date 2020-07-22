package com.nytimes.support

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.nytimes.R
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun String.formatDateTime(inputFormat: String?, outputFormat: String?): String? {
    return try {
        var format = SimpleDateFormat(inputFormat, Locale.ENGLISH)
        var newDate: Date? = null
        newDate = format.parse(this)
        format = SimpleDateFormat(outputFormat)
        format.format(newDate)
    } catch (e: Exception) {
        Timber.e(e.message)
        e.printStackTrace().toString()
    }
}

fun ImageView.setImageFromUrl(url: String) {
    if (url.isNotEmpty()) {
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).fit().centerCrop().into(this)
    } else {
        Picasso.get().load(R.drawable.ic_launcher_foreground).placeholder(R.drawable.ic_launcher_foreground).fit()
            .centerCrop().into(this)
    }
}




