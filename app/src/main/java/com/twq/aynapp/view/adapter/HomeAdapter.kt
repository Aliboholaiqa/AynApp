package com.twq.aynapp.view.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.saved.SavedFragment

class HomeAdapter (var data:List<Project>): RecyclerView.Adapter<HomeHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_home_posts,parent,false)
        return HomeHolder(v)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.vtitle.text = data[position].projectTitle
        holder.vusername.text = data[position].createdAt
        Picasso.get().load(data[position].image).into(holder.vimage)

        holder.vbuttonSave.setOnClickListener {
            holder.vbuttonSave.setImageResource(R.drawable.ic_bookmarkbold)
            val intent = Intent(holder.itemView.context, SavedFragment::class.java)
            intent.putExtra("saved",data[position])
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProjectDetailsActivity::class.java)
            intent.putExtra("project",data[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return data.size
    }

}

class HomeHolder(v: View): RecyclerView.ViewHolder(v){
    var vtitle = v.findViewById<TextView>(R.id.textViewProjectTitle)
    var vusername= v.findViewById<TextView>(R.id.textViewUsername)
    var vimage = v.findViewById<ImageView>(R.id.imageViewPost)
    var vbuttonSave = v.findViewById<ImageButton>(R.id.imageButtonSave)
}