package com.twq.aynapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityHomeDetailsBinding
import com.twq.aynapp.model.Car

class HomeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var project = intent.getSerializableExtra("project") as Car
        binding.textViewDetailsProjectTitle.text = project.carName
        Picasso.get().load(project.carImage).into(binding.imageViewDetails1)
        setContentView(binding.root)

    }
}