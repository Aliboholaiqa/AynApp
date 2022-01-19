package com.twq.aynapp.view.profile

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
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

        binding.imageViewBackButton.setOnClickListener {
            finish()
        }

        //Getting user info
        val user = intent.getSerializableExtra("profile") as User
        var userID =intent.getStringExtra("fb_id")

        if (userID==null)
            userID=user.fb_id

        binding.imageButtonContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data= Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email))
            intent.putExtra(Intent.EXTRA_SUBJECT,"Contacting from Ayn app")
            intent.putExtra(Intent.EXTRA_TEXT,"Greetings ${user.username}, ")
            startActivity(intent)
        }

        binding.textViewOtherProfileUsername.text = user.username
        binding.textViewOtherProfileBio.text = user.bio
        Picasso.get().load(user.avatar).into(binding.imageViewOtherProfileAvatar)
        Picasso.get().load(user.header).into(binding.imageViewOtherHeader)

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Waiting for projects ")
        progressDialog.show()

        val oRecyclerView = findViewById<RecyclerView>(R.id.oRecyclerView)
        oRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        vm.getUserProject(userID).observe(this,{
            oRecyclerView.adapter = ProfileAdapter(it)
            progressDialog.dismiss()
        })
    }
}