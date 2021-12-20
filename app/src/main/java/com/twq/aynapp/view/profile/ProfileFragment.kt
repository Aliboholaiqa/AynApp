package com.twq.aynapp.view.profile

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.model.*
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.home.HomeAdapter
import com.twq.aynapp.view.home.HomeViewModel
import java.io.File
import java.util.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val db = FirebaseFirestore.getInstance()
        val auth = Firebase.auth
        val vm : ProfileViewModel by viewModels()

        val username = v.findViewById<TextView>(R.id.textViewProfileUsername)
        val bio = v.findViewById<TextView>(R.id.textViewProfileBio)
        val image = v.findViewById<ImageView>(R.id.imageViewProfileAvatar)
        var header = v.findViewById<ImageView>(R.id.imageViewHeader)

        // get profile info from view model
        db.collection("user").document(auth.currentUser?.uid.toString())
            .addSnapshotListener { user, error ->
                if(user !=null){
                    username.text = user.getString("username")
                    bio.text = user.getString("bio")

                    val fileName = user.getString("avatar")
                    val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
                    val localFile = File.createTempFile("tempImg","jpg")
                    refStorage.getFile(localFile).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                        image.setImageBitmap(bitmap)
                    }.addOnFailureListener{e->
                        Log.d("Doc","Failed to get an image")
                    }
                }
            }

        val buttonProfileEdit = v.findViewById<ImageButton>(R.id.buttonProfileEditInfo)
        buttonProfileEdit.setOnClickListener {
            val intent = Intent (context, ProfileEditInfoActivity::class.java)
            startActivity(intent)
        }
        val buttonAdd = v.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        buttonAdd.setOnClickListener {
            val intent = Intent (context, ProfileAddProjectActivity::class.java)
            startActivity(intent)
        }

        val pRecyclerView = v.findViewById<RecyclerView>(R.id.pRecyclerView)
        pRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        vm.getProjectData().observe(this,{
            pRecyclerView.adapter = ProfileAdapter(it)
        })

        return v
    }
}