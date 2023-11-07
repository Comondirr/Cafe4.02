package com.example.cafe40

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CoffiActivity : AppCompatActivity() {
    private lateinit var adapter: CoffeeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffi)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CoffeeAdapter()
        recyclerView.adapter = adapter

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Coffee")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val coffeeList = dataSnapshot.children.mapNotNull { it.getValue(Coffee::class.java) }
                adapter.submitList(coffeeList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок
            }
        })

    }
}
