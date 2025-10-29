package com.example.wikigifty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.view.View

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Firebase 초기화
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // UI 요소 찾기
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val birthDateInput = findViewById<EditText>(R.id.birthDateInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirmPasswordInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val birthDate = birthDateInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            fun setLoading(loading: Boolean) {
                findViewById<View>(R.id.progress).visibility = if (loading) View.VISIBLE else View.GONE
                registerButton.isEnabled = !loading
            }

            // Hide keyboard
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            // 기본 유효성 검사
            if (name.isEmpty() || birthDate.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_fill_all_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // YYYY-MM-DD simple check
            val birthRegex = Regex("^\\d{4}-\\d{2}-\\d{2}$")
            if (!birthRegex.containsMatchIn(birthDate)) {
                Toast.makeText(this, getString(R.string.birthdate_hint), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Phone number with at least 8 digits
            val phoneDigits = phone.filter { it.isDigit() }
            if (phoneDigits.length < 8) {
                Toast.makeText(this, getString(R.string.phone_number), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, getString(R.string.toast_invalid_email), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(this, getString(R.string.toast_password_min_length), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 비밀번호 확인
            if (password == confirmPassword) {
                // Firebase Auth를 통한 사용자 생성
                setLoading(true)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // 사용자 ID 가져오기
                            val userId = auth.currentUser?.uid
                            val user = mapOf(
                                "name" to name,
                                "birthDate" to birthDate,
                                "phone" to phone,
                                "email" to email
                            )
                            // Firebase Database에 사용자 정보 저장
                            if (userId == null) {
                                Toast.makeText(this, getString(R.string.toast_user_not_found_after_signup), Toast.LENGTH_SHORT).show()
                                setLoading(false)
                                return@addOnCompleteListener
                            }
                            database.child("users").child(userId).setValue(user)
                                .addOnCompleteListener { dbTask ->
                                    setLoading(false)
                                    if (dbTask.isSuccessful) {
                                        Toast.makeText(this, getString(R.string.toast_register_success), Toast.LENGTH_SHORT).show()
                                        // 프로필 화면으로 이동
                                        val intent = Intent(this, ProfileActivity::class.java)
                                        intent.putExtra("userId", userId)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(this, getString(R.string.toast_db_error, dbTask.exception?.message ?: ""), Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } else {
                            setLoading(false)
                            Toast.makeText(this, getString(R.string.toast_register_failed, task.exception?.message ?: ""), Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, getString(R.string.toast_passwords_not_match), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
