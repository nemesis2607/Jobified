package com.example.jobified

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: CandidateAdapter
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val query = FirebaseDatabase.getInstance().reference.child("Product")
        val options =
            FirebaseRecyclerOptions.Builder<Candidate>().setQuery(query, Candidate::class.java).build()

        adapter = CandidateAdapter(options)


        val menubtn : ImageView = findViewById(R.id.menubtn)

        menubtn.setOnClickListener{
            val intent = Intent(this, Logout::class.java)
            startActivity(intent)
        }

        val rView: RecyclerView = findViewById(R.id.rView)
        rView.layoutManager = LinearLayoutManager(this)
        rView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
