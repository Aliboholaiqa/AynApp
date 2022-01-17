package com.twq.aynapp.view.profile

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileForOtherUsersBinding
import com.twq.aynapp.model.Project
import com.twq.aynapp.model.User

class ProfileForOtherUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileForOtherUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vm : ProfileViewModel by viewModels()
        val auth = Firebase.auth

        //Getting user info
        val user = intent.getSerializableExtra("profile") as User

        binding.textViewOtherProfileUsername.text = user.username
        binding.textViewOtherProfileBio.text = user.bio
        Picasso.get().load(user.avatar).into(binding.imageViewOtherProfileAvatar)

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Waiting for projects ")
        progressDialog.show()

        val oRecyclerView = findViewById<RecyclerView>(R.id.oRecyclerView)
        oRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        vm.getUserProject(user.id).observe(this,{
            oRecyclerView.adapter = ProfileAdapter(it)
            progressDialog.dismiss()
        })
    }
}