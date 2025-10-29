package com.example.wikigifty

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class WikiGiftyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        } catch (_: Exception) {
            // Already enabled in this process; ignore
        }
    }
}


