package com.twq.aynapp.view.saved

import android.content.ContentValues
import com.twq.aynapp.view.home.ProjectDetailsActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.Project

class SavedAdapter (var data:List<Project>): RecyclerView.Adapter<SavedHolder>(){
    val auth = Firebase.auth
    val db = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_home_posts,parent,false)
        return SavedHolder(v)
    }

    override fun onBindViewHolder(holder: SavedHolder, position: Int) {
        holder.vtitle.text = data[position].projectTitle
        holder.vusername.text = data[position].createdAt
        Picasso.get().load(data[position].image).into(holder.vimage)
        holder.vbuttonSave.setImageResource(R.drawable.ic_bookmarkbold)
        holder.vbuttonSave.setOnClickListener {
            //delete
            db.collection("user").document(auth.currentUser?.uid.toString())
                .collection("saved").document(data[position].id!!)
                .delete()
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG,"Successfully deleted") }
                .addOnFailureListener { Log.d(ContentValues.TAG,"Failed to delete ")
                }
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

class SavedHolder(v: View): RecyclerView.ViewHolder(v){
    var vtitle = v.findViewById<TextView>(R.id.textViewProjectTitle)
    var vusername= v.findViewById<TextView>(R.id.textViewUsername)
    var vimage = v.findViewById<ImageView>(R.id.imageViewPost)
    var vbuttonSave = v.findViewById<ImageButton>(R.id.imageButtonSave)
}