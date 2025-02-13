package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    lateinit var emailField: EditText
    lateinit var passwordField: TextInputLayout
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailField = findViewById<EditText>(R.id.emailField)
        passwordField = findViewById<TextInputLayout>(R.id.passwordField)
        button = findViewById<Button>(R.id.loginButton)

        Log.i("DIBIDI", button?.text.toString())

        button.setOnClickListener {
            val email = emailField?.text.toString()
            val password = passwordField.editText?.text.toString()

            lifecycleScope.launch {
                val result = SupabaseClient.signIn(email, password)
                onSignInResult(result)
            }
        }

    }

    fun onSignInResult(result: Boolean) {
        if (result) {
            val intent: Intent = Intent(this, ListActivity2::class.java)
            startActivity(intent)
        }
    }
}