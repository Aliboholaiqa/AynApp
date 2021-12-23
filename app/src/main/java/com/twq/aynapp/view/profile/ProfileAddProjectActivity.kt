package com.twq.aynapp.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileAddProjectBinding

class ProfileAddProjectActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileAddProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}