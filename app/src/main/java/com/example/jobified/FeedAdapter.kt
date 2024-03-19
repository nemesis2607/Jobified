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

class FeedAdapter(options: FirebaseRecyclerOptions<Feed>) :
    FirebaseRecyclerAdapter<Feed, FeedAdapter.FeedViewHolder>(options) {

    public class FeedViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.feedcard, parent, false)) {

        val name: TextView = itemView.findViewById(R.id.user)
        val feed: TextView = itemView.findViewById(R.id.feed)
        val time: TextView = itemView.findViewById(R.id.time)
        val photo: ImageView = itemView.findViewById(R.id.imageUrl)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeedViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int, model: Feed) {
        val fModel: Feed = model
        val storRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(fModel.imageUrl)

        Glide.with(holder.photo.context)
            .load(storRef)
            .into(holder.photo)

        holder.name.text = fModel.user
        holder.feed.text = fModel.post
        holder.time.text = fModel.time


    }


}