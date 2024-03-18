package com.example.jobified

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CandidateAdapter(options: FirebaseRecyclerOptions<Candidate>) :
    FirebaseRecyclerAdapter<Candidate, CandidateAdapter.ProductViewHolder>(options) {

    public class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.candidatecard, parent, false)) {

        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.desc)
        val photo: ImageView = itemView.findViewById(R.id.imageUrl)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int, model: Candidate) {
        val pModel: Candidate = model


        val storRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(pModel.imageUrl)


        Glide.with(holder.photo.context)
            .load(storRef)
            .into(holder.photo)



        holder.name.text = pModel.name
        holder.description.text = pModel.description


        holder.itemView.setOnClickListener {
            // Create an intent to start DetailActivity
            val intent = Intent(it.context, DetailsActivity::class.java)

            // Pass the selected product to DetailActivity
            intent.putExtra("PRODUCT", pModel)

            // Start the activity
            it.context.startActivity(intent)
        }


    }


}
