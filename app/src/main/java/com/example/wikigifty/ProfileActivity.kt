package com.example.wikigifty

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.View

class ProfileActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val profileName = findViewById<TextView>(R.id.profileName)
        val profileBirthDate = findViewById<TextView>(R.id.profileBirthDate)
        val profilePhone = findViewById<TextView>(R.id.profilePhone)
        val profileEmail = findViewById<TextView>(R.id.profileEmail)
        val progress = findViewById<View>(R.id.progress)

        // Firebase Database 초기화
        database = FirebaseDatabase.getInstance().reference

        // 전달된 사용자 ID 또는 현재 로그인 사용자 ID 사용
        val userId = intent.getStringExtra("userId") ?: FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            // 비로그인 상태 접근 방지
            finish()
            return
        }

        // 데이터베이스에서 사용자 정보 가져오기
        progress.visibility = View.VISIBLE
        database.child("users").child(userId).get().addOnSuccessListener { snapshot ->
            val name = snapshot.child("name").value.toString()
            val birthDate = snapshot.child("birthDate").value.toString()
            val phone = snapshot.child("phone").value.toString()
            val email = snapshot.child("email").value.toString()

            // UI에 정보 표시
            profileName.text = getString(R.string.label_name, name)
            profileBirthDate.text = getString(R.string.label_birthdate, birthDate)
            profilePhone.text = getString(R.string.label_phone, phone)
            profileEmail.text = getString(R.string.label_email, email)
            progress.visibility = View.GONE
        }.addOnFailureListener {
            profileName.text = getString(R.string.profile_load_failed)
            progress.visibility = View.GONE
        }
    }
}
