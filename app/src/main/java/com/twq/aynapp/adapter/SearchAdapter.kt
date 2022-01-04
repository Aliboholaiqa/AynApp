package com.twq.aynapp.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.User
import com.twq.aynapp.view.profile.ProfileHolder

class SearchAdapter (var data: List<User>): RecyclerView.Adapter<SearchHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_search,parent,false)
        return SearchHolder(v)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.vusername.text = data[position].username
        Picasso.get().load(data[position].avatar).into(holder.vavatar)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}

class SearchHolder(v:View): RecyclerView.ViewHolder(v){
    var vusername= v.findViewById<TextView>(R.id.textViewSearchUsername)
    var vavatar = v.findViewById<ImageView>(R.id.imageViewSearchAvatar)
}