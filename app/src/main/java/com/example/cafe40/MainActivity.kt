package com.example.cafe40

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        })


        val regButton = findViewById<Button>(R.id.regButton)
        regButton.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }

        val buttonLogout = findViewById<Button>(R.id.buttonloguot)
        buttonLogout.setOnClickListener {
            auth.signOut()

            val sharedPreferences = getSharedPreferences("com.example.cafe40", Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("isChecked", false).apply()

            Toast.makeText(this, "Успешно вышли из системы", Toast.LENGTH_SHORT).show()
        }




    }
}
