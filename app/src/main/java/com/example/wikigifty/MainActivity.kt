package com.example.wikigifty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FirebaseAuth 초기화
        auth = FirebaseAuth.getInstance()

        // 현재 사용자 정보 가져오기
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // 로그인 필요 시 로그인 화면으로 이동
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        // 필요 시 사용자 정보 사용
        println("Logged in as: ${currentUser.email}")

        // 프로필 보기 버튼
        findViewById<Button>(R.id.profileButton).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        // 로그아웃 버튼
        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
