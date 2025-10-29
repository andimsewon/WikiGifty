package com.example.wikigifty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.view.View

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton) // 회원가입 버튼

        // 로그인 버튼 클릭
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            fun setLoading(loading: Boolean) {
                findViewById<View>(R.id.progress).visibility = if (loading) View.VISIBLE else View.GONE
                loginButton.isEnabled = !loading
                registerButton.isEnabled = !loading
            }

            // 키보드 숨김
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            if (email.isNotEmpty() && password.isNotEmpty()) {
                setLoading(true)
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        setLoading(false)
                        if (task.isSuccessful) {
                            Toast.makeText(this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, getString(R.string.toast_login_failed, task.exception?.message ?: ""), Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, getString(R.string.toast_enter_email_password), Toast.LENGTH_SHORT).show()
            }
        }

        // 회원가입 버튼 클릭 이벤트
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent) // RegisterActivity로 이동
        }
    }

    override fun onStart() {
        super.onStart()
        // 이미 로그인된 경우 메인으로 이동
        FirebaseAuth.getInstance().currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
