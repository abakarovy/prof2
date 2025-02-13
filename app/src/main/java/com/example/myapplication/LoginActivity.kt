package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var emailField: EditText
    private lateinit var passwordField: TextInputLayout
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailField = findViewById(R.id.emailField)
        passwordField = findViewById(R.id.passwordField)
        button = findViewById(R.id.loginButton)

        button.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.editText?.text.toString()

            if (!TextValidator.validateEmail(email)) {
                invalidEmailDialog()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = SupabaseClient.signIn(email, password)
                onSignInResult(result)
            }
        }

    }

    private fun onSignInResult(result: Boolean) {
        Log.i("AAA", result.toString())
        if (result) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun invalidEmailDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilder
            .setIcon(getDrawable(R.drawable.ic_launcher_foreground))
            .setTitle("Invalid Email!")
            .setPositiveButton("OK") { dialog, btnCode ->
                button.text = "Войти"
                dialog.dismiss()
            }

        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }
}