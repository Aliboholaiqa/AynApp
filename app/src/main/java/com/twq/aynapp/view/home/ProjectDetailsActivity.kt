package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.twq.aynapp.databinding.ActivityProjectDetailsBinding
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.profile.ProfileViewModel

class ProjectDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vm : ProfileViewModel by viewModels()
        val project = intent.getSerializableExtra("project") as Project
        vm.getUserData().observe(this,{
            binding.textViewDetailsUsername.text = it.username
        })
        binding.textViewDetailsProjectTitle.text = project.projectTitle
        binding.textViewDescription.text = project.description
        binding.textViewProjectDate.text = project.createdAt
        Picasso.get().load(project.image).into(binding.imageViewDetails)

    }
}