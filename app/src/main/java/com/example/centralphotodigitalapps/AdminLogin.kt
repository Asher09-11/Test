package com.example.centralphotodigitalapps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.ImageButton

class AdminLogin : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var logoCPD: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.loginbtn)
        logoCPD = findViewById(R.id.logoCPD)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            // Check if entered email and password match the expected values
            if (email == "justintunggal8@gmail.com" && password == "justintunggalganteng69") {
                val intent = Intent(this@AdminLogin, MainActivity::class.java)
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this@AdminLogin, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
