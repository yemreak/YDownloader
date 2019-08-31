package com.yedhrab.ydownloader

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLException
import com.yausername.youtubedl_android.YoutubeDLRequest
import java.io.File
import com.yausername.youtubedl_android.mapper.VideoInfo
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Arkaplanda çalıştırma
        moveTaskToBack(true)
        if (intent?.type == "text/plain") {
            // Handle intents with text ...
            handleSendText(intent)
        }

        finish()

        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_share)
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            // Update UI to reflect text being shared
            Log.i("YoutubeDL", it)
            downloadUrl(it)
        }
    }

    private fun downloadUrl(url: String) {
        val youtubeDLDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "YDownloader"
        )
        val request = YoutubeDLRequest(url)
        request.setOption("-o", youtubeDLDir.absolutePath + "/" + "%(id)s.%(ext)s")
        YoutubeDL.getInstance()
            .execute(request) { progress, etaInSeconds -> Log.i("YoutubeDL", "$progress% (ETA $etaInSeconds seconds)") }
    }

}
