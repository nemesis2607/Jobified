package com.example.jobified

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CandidateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent

        // Retrieve the product object from the intent
        val product: Candidate? = intent.getParcelableExtra("PRODUCT")


        val name: TextView = findViewById(R.id.name)
        val desc: TextView = findViewById(R.id.desc)


        val image: ImageView = findViewById(R.id.image)
        val buyBtn: Button = findViewById(R.id.buyBtn)

        val storRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl("${product?.imageUrl}")

        Log.d("Glide", "Loading image with URL: ${product?.imageUrl}")
        Glide.with(this@CandidateActivity).load(storRef).into(image)

        // Set the values based on the product object
        name.text = product?.name
        desc.text = product?.description


        buyBtn.setOnClickListener{
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent)
        }


    }
}