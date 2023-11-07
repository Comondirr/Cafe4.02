package com.example.cafe40

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.reg_btn)
        registerButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.reg_username).text.toString()
            val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.apply()
            val email = findViewById<EditText>(R.id.reg_email).text.toString()
            val password = findViewById<EditText>(R.id.reg_password).text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Регистрация прошла успешно!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val user = auth.currentUser

                        // Переходите к следующей активности после успешной регистрации
                        val intent = Intent(this, LogActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Ошибка регистрации.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

        // Обработчик кнопки "Назад"
        val backButton = findViewById<Button>(R.id.back_btn_reg)
        backButton.setOnClickListener {
            finish()
        }
    }
}

