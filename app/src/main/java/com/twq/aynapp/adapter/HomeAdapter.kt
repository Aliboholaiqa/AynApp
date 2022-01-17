package com.twq.aynapp.view.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.MessagePayloadKeys.SENDER_ID
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.messaging.ktx.remoteMessage
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.saved.SavedFragment

class HomeAdapter (var data:List<Project>): RecyclerView.Adapter<HomeHolder>(){
    val auth = Firebase.auth
    val db = Firebase.firestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_home_posts,parent,false)
        return HomeHolder(v)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.vtitle.text = data[position].projectTitle
        holder.vusername.text = data[position].createdAt
        Picasso.get().load(data[position].image).into(holder.vimage)

        holder.vbuttonSave.setOnClickListener {
            val project = hashMapOf(
                "date" to data[position].createdAt,
                "title" to data[position].projectTitle,
                "description" to data[position].description,
                "image" to data[position].image,
                "userId" to data[position].userId
            )
            db.collection("user").document(auth.currentUser?.uid.toString())
                .collection("saved").add(project).addOnCompleteListener {
                    if (it.isSuccessful){
                        val fm = Firebase.messaging
                        fm.send(remoteMessage("$SENDER_ID@fcm.googleapis.com") {
                            setMessageId("messageId.toString()")
                            addData("my_message", "Hello World")
                            addData("my_action", "SAY_HELLO")
                        })
                        Log.d("Doc","Saved project successfully")
                    }else{
                        Log.d("Doc","Failed to save project")
                    }
                }.addOnFailureListener {
                    Log.d("Doc","Failure to save project")
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

class HomeHolder(v: View): RecyclerView.ViewHolder(v){
    var vtitle = v.findViewById<TextView>(R.id.textViewProjectTitle)
    var vusername= v.findViewById<TextView>(R.id.textViewUsername)
    var vimage = v.findViewById<ImageView>(R.id.imageViewPost)
    var vbuttonSave = v.findViewById<ImageButton>(R.id.imageButtonSave)

}