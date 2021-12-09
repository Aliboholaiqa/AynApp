package com.twq.aynapp.model


import com.twq.aynapp.R
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class ProfileAdapter(var data:MutableList<Movie>): RecyclerView.Adapter<ProfileMovieHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_profile_posts,parent,false)

        return ProfileMovieHolder(v)
    }

    override fun onBindViewHolder(holder: ProfileMovieHolder, position: Int) {
        holder.textViewProfileTitle.text = data[position].title
        Picasso.get().load(data[position].posterURL).into(holder.imageViewProfilePoster)

    }

    fun deleteItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun getItemCount(): Int {
        return data.size
    }


}
class ProfileMovieHolder(v: View) : RecyclerView.ViewHolder(v){
    var imageViewProfilePoster = v.findViewById<ImageView>(R.id.imageViewProfilePost)
    var textViewProfileTitle = v.findViewById<TextView>(R.id.textViewProfileProjectTitle)

}
