package com.twq.aynapp.view.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.Project

class ProfileAdapter (var data:List<Project>): RecyclerView.Adapter<ProfileHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_profile_posts,parent,false)
        return ProfileHolder(v)
    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedBytes = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
        holder.vtitle.text = data[position].projectTitle
        Picasso.get().load(data[position].image).placeholder(R.drawable.ic_launcher_background).into(holder.vimage)

//        if (data[position].image.length>30){
//            holder.vimage.setImageBitmap(decodeBase64(data[position].image))
//        }else{
//            Picasso.get().load(data[position].image).into(holder.vimage)
//        }
    }

    override fun getItemCount(): Int {

        return data.size
    }

}

class ProfileHolder(v: View): RecyclerView.ViewHolder(v){
    var vtitle = v.findViewById<TextView>(R.id.textViewProfileProjectTitle)
    var vimage = v.findViewById<ImageView>(R.id.imageViewProfilePost)
}