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


class MovieAdapter(var data:MutableList<Movie>): RecyclerView.Adapter<MovieHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_home_posts,parent,false)

        return MovieHolder(v)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.textViewTitle.text = data[position].title
        holder.textViewYear.text = data[position].year.toString()
        Picasso.get().load(data[position].posterURL).into(holder.imageViewPoster)

    }

    fun deleteItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun getItemCount(): Int {
        return data.size
    }


}
class MovieHolder(v: View) : RecyclerView.ViewHolder(v){
    var imageViewPoster = v.findViewById<ImageView>(R.id.imageViewPost)
    var textViewTitle = v.findViewById<TextView>(R.id.textViewProjectTitle)
    var textViewYear = v.findViewById<TextView>(R.id.textViewUsername)
    var imageViewSaved = v.findViewById<ImageView>(R.id.imageViewSave)

}
