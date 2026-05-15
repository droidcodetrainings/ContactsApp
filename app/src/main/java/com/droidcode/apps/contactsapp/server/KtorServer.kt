package com.droidcode.apps.contactsapp.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class KtorServer: Service() {

    override fun onBind(p0: Intent?): IBinder?  = null

    override fun onCreate() {
        Log.i("KtorServer", "Starting Ktor server")
        super.onCreate()
    }

}