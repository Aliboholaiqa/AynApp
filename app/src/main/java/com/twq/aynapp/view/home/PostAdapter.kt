package com.twq.aynapp.view.home


import Post
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.aynapp.R


class PostAdapter (var data: List<Post>): RecyclerView.Adapter<UserHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_home_posts,parent,false)
        return UserHolder(v)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.textViewName.text = data[position].name
       // holder.textViewProjectTitle.text = data[position]
        Picasso.get().load(data[position].image).placeholder(R.drawable.ic_launcher_background).into(holder.image)

//        holder.itemView.setOnClickListener {
//            var intent = Intent(holder.itemView.context, ProfileActivity::class.java)
//            intent.putExtra("user",data[position])
//            holder.itemView.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class UserHolder(v: View): RecyclerView.ViewHolder(v){
    var textViewName = v.findViewById<TextView>(R.id.textViewUsername)
    var textViewProjectTitle = v.findViewById<TextView>(R.id.textViewProjectTitle)
    var image = v.findViewById<ImageView>(R.id.imageViewPost)


}