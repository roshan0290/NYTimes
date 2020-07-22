package com.nytimes.support

import android.app.ProgressDialog
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.Window
import com.nytimes.R
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

object Utility {

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog(context: Context) {
        if (progressDialog != null && progressDialog!!.isShowing()) {

        } else {
            progressDialog = ProgressDialog(context)
            val inflater = LayoutInflater.from(context)
            progressDialog!!.getWindow()!!.requestFeature(Window.FEATURE_NO_TITLE)
            progressDialog!!.getWindow()!!
                .setBackgroundDrawableResource(android.R.color.transparent)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.show()
            progressDialog!!.setContentView(inflater.inflate(R.layout.progress_dialog, null))
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing()) {
            progressDialog!!.dismiss()
        }
    }


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    // Method to save an bitmap to a file
     fun bitmapToFile(bitmap: Bitmap, context: Context): Uri {
        // Get the context wrapper
        val wrapper = ContextWrapper(context)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images",Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        try{
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return Uri.parse(file.absolutePath)
    }





}