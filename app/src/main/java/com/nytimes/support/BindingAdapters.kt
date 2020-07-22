package com.sdei.woundspro.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nytimes.support.formatDateTime
import com.nytimes.support.setImageFromUrl

object BindingAdapters {

    @BindingAdapter("android:setImage")
    @JvmStatic
    fun ImageView.setImage(url: String?) {
        this.setImageFromUrl(url ?: "")
    }

    //2019-12-05T00:00:00
    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:date")
    @JvmStatic
    fun TextView.date(date: String?) {
        this.setText(date?.formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "MMM dd"))
    }
    //2019-12-05T00:00:00
    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:time")
    @JvmStatic
    fun TextView.time(date: String?) {
        this.setText(date?.formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "HH:mm"))
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:monthdate")
    @JvmStatic
    fun TextView.monthdate(date: String?) {
        this.setText(date?.formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "DD dd"))
    }



    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:datemm")
    @JvmStatic
    fun TextView.datemm(datemm: String?) {
        this.setText(datemm?.formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "dd/MM/yyyy"))
    }
}