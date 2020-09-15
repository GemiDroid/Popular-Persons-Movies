package com.gemidroid.presentation

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gemidroid.moviesdb.R
import com.gemidroid.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_full_screen_profile.*

class FullScreenProfileActivity : AppCompatActivity() {

    private var mgr: DownloadManager? = null
    private var imageUrl: String? = null
    private var hasCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_profile)

        intent.extras?.getString(Constants.IMAGE_KEY).let { imageFullPath ->
            imageUrl = imageFullPath
            Glide.with(this)
                .load(imageFullPath)
                .error(R.drawable.ic_launcher_foreground)
                .into(img_profile_full_screen)
        }

        mgr = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )

        registerReceiver(
            onNotificationClick,
            IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        )

        txt_download_title.setOnClickListener {
            if (hasCompleted)
                viewDownloadedImage()
            else
                startDownload(it)
        }

        img_close.setOnClickListener {
            finish()
        }
    }

    private fun viewDownloadedImage() {
        startActivity(Intent(DownloadManager.ACTION_VIEW_DOWNLOADS))
    }

    private fun changeDownloadStatus() {
        txt_download_title.isEnabled = true
        txt_download_title.text = getString(R.string.downloaded_image)
        txt_download_title.setCompoundDrawables(null, null, null, null)
    }

    private fun startDownload(v: View) {
        RxPermissions(this).request(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .subscribe { granted ->
                if (!granted) {
                    Snackbar.make(v, "Please give permission", Snackbar.LENGTH_SHORT).show()
                    return@subscribe
                }
                val uri: Uri = Uri.parse(imageUrl)

                mgr!!.enqueue(
                    DownloadManager.Request(uri)
                        .setAllowedNetworkTypes(
                            DownloadManager.Request.NETWORK_WIFI or
                                    DownloadManager.Request.NETWORK_MOBILE
                        )
                        .setAllowedOverRoaming(false)
                        .setTitle("Image Profile")
                        .setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_DOWNLOADS,
                            "${imageUrl}.jpg"
                        )
                )
                v.isEnabled = false

            }
    }

    private var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context?, intent: Intent?) {
            changeDownloadStatus()
            hasCompleted = true
        }
    }

    private var onNotificationClick: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context?, intent: Intent?) {
            if (hasCompleted)
                viewDownloadedImage()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onComplete)
        unregisterReceiver(onNotificationClick)
    }
}
