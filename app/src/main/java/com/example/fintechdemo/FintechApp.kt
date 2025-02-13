package com.example.fintechdemo

import android.app.Application
import com.google.firebase.FirebaseApp

class FintechApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
