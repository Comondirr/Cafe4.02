package com.example.cafe40

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("com.example.auto3", Context.MODE_PRIVATE)

        val loginButton = findViewById<Button>(R.id.login_btn)
        val checkBoxMemory = findViewById<CheckBox>(R.id.CheckboxMemory)

        // Проверьте, был ли пользователь уже вошел в систему
        if (sharedPreferences.getBoolean("isChecked", false)) {
            val intent = Intent(this, CoffiActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.login_email).text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Вход выполнен успешно!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Сохраните состояние CheckBox и данные пользователя, если CheckBox выбран
                        if (checkBoxMemory.isChecked) {
                            sharedPreferences.edit().putBoolean("isChecked", true).apply()
                            sharedPreferences.edit().putString("email", email).apply()
                            sharedPreferences.edit().putString("password", password).apply()
                        }

                        val intent = Intent(this, CoffiActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Ошибка входа.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

        val backButton = findViewById<Button>(R.id.back_btn_login)
        backButton.setOnClickListener {
            finish()
        }
    }
}