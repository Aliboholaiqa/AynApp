package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityHomeDetailsBinding
import com.twq.aynapp.model.Project

class HomeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val project = intent.getSerializableExtra("project") as Project
        binding.textViewDetailsProjectTitle.text = project.projectTitle
        binding.textViewDescription.text = project.description
        Picasso.get().load(project.image).into(binding.imageViewDetails)

    }
}