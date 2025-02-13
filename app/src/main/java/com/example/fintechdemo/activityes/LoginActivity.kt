package com.example.fintechdemo.activityes

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fintechdemo.R
import com.example.fintechdemo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

// login Credential
// Email - Dhaval@gmail.com
// Password - 123456

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.emailInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        // Validate Email and Password
        when {
            email.isEmpty() -> {
                binding.emailInput.error = getString(R.string.email_is_required)
                binding.emailInput.requestFocus()
                return
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.emailInput.error = getString(R.string.enter_a_valid_email_address)
                binding.emailInput.requestFocus()
                return
            }

            password.isEmpty() -> {
                binding.passwordInput.error = getString(R.string.password_is_required)
                binding.passwordInput.requestFocus()
                return
            }

            password.length < 6 -> {
                binding.passwordInput.error =
                    getString(R.string.password_must_be_at_least_6_characters)
                binding.passwordInput.requestFocus()
                return
            }
        }

        binding.progressBar.visibility = View.VISIBLE

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, getString(R.string.authentication_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
