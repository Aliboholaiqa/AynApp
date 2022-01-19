package com.twq.aynapp.view.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProjectDetailsBinding
import com.twq.aynapp.model.Project
import com.twq.aynapp.model.User
import com.twq.aynapp.utility.CheckState
import com.twq.aynapp.view.profile.ProfileForOtherUsers
import com.twq.aynapp.view.profile.ProfileViewModel
import java.util.*

class ProjectDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vmProfile : ProfileViewModel by viewModels()
        val project = intent.getSerializableExtra("project") as Project

        binding.textViewDetailsProjectTitle.text = project.projectTitle
        binding.textViewDescription.text = project.description
        binding.textViewProjectDate.text = project.createdAt
        Picasso.get().load(project.image).into(binding.imageViewDetails)

        vmProfile.getUserByID(project.userId).observe(this,{
            binding.textViewDetailsUsername.text = it.username
            Picasso.get().load(it.avatar).into(binding.imageViewProjectAvatar)
            val user = it
            binding.textViewDetailsUsername.setOnClickListener {
                val intent = Intent(this,ProfileForOtherUsers::class.java)
                intent.putExtra("profile", user)
                intent.putExtra("fb_id",project.userId)
                startActivity(intent)
            }
        })

        if (CheckState().checkState(project.userId)){
            binding.buttonEditProject.isVisible = true
            binding.buttonDeleteProject.isVisible = true
        }else{
            binding.buttonEditProject.isVisible = false
            binding.buttonDeleteProject.isVisible = false
        }

        binding.imageViewBack.setOnClickListener {
            finish()
        }

        binding.buttonEditProject.setOnClickListener {
            val intent = Intent(this,ProjectEditActivity::class.java)
            intent.putExtra("project",project)
            startActivity(intent)
        }

        binding.buttonDeleteProject.setOnClickListener {
            val dialogDeleteView = layoutInflater.inflate(R.layout.custom_layout_delete,null)
            val deleteDialog = AlertDialog.Builder(this).setView(dialogDeleteView).create()
            deleteDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            deleteDialog.show()

            val buttonDeleteItem = dialogDeleteView.findViewById<Button>(R.id.buttonDeleteItem)

            buttonDeleteItem.setOnClickListener {
                vmProfile.deleteProject(project.id!!)
                deleteDialog.dismiss()
                finish()
            }
            val buttonCancelItem = dialogDeleteView.findViewById<Button>(R.id.buttonCancelItem)
            buttonCancelItem.setOnClickListener {
                deleteDialog.dismiss()
            }
        }
    }

}
