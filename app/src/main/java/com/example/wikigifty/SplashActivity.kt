package com.example.wikigifty

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 1초 후 현재 로그인 상태에 따라 화면 전환
        Handler(Looper.getMainLooper()).postDelayed({
            val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
            val next = if (isLoggedIn) MainActivity::class.java else LoginActivity::class.java
            startActivity(Intent(this, next))
            finish()
        }, 1000) // 1000 milliseconds = 1초
    }
}
