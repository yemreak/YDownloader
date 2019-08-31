package com.yedhrab.ydownloader

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isStoragePermissionGranted()) {
            initializeYoutubeDL()
        } else {
            finish()
        }

        // You would then use these three variable to figure out who has sent you an Intent and what they want you to do!
        // See here for further instruction: https://developer.android.com/training/sharing/receive.html
        // https://developer.android.com/guide/topics/manifest/action-element.html
        // https://developer.android.com/reference/android/content/Intent.html#ACTION_GET_CONTENT

    }

    private fun initializeYoutubeDL() {
        try {
            YoutubeDL.getInstance().init(application)
            Log.i("YoutubeDL", "Kurulum tamamlandı 🥳")
        } catch (e: YoutubeDLException) {
            Log.e("Hata", "failed to initialize youtubedl-android", e)
        }
    }

    private fun isStoragePermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(applicationContext, "İznine ihtiyacım var 🤕", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1)
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            return true
        }

        return false
    }
}


