package com.example.cafe40

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        })


        val regButton = findViewById<Button>(R.id.regButton)
        regButton.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        })
    }
}