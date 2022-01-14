package com.twq.aynapp.view.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProjectEditBinding
import com.twq.aynapp.model.Project
import com.twq.aynapp.view.profile.ProfileViewModel
import java.util.*

class ProjectEditActivity : AppCompatActivity() {
    lateinit var binding:ActivityProjectEditBinding
    val vm: ProfileViewModel by viewModels()
    lateinit var project: Project
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        project = intent.getSerializableExtra("project") as Project

        binding.editTextEditProjectTitle.setText(project.projectTitle)
        binding.editTextEditProjectDescription.setText(project.description)
        Picasso.get().load(project.image).into(binding.imageViewEditProjectImage)

        binding.floatingActionButtonEditImage.setOnClickListener {
            selectImageFromGallery()
        }

        //edit
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = ("$day/${month+1}/$year")
        binding.buttonEditProjectPage.setOnClickListener {
            vm.editProject(project.id!!,binding.editTextEditProjectTitle.text.toString(),
                binding.editTextEditProjectDescription.text.toString(),date).observe(this,{
                    finish()
            })
        }

        binding.buttonCancelEditing.setOnClickListener {
            finish()
        }
    }

    //image
    private fun selectImageFromGallery() {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Get the Uri of data
            val file_uri = data?.data
            if (file_uri != null) {
                binding.imageViewEditProjectImage.setImageURI(file_uri)

                vm.uploadImageToFirebase(file_uri).observe(this,{
                    vm.updateProjectImg(it, project.id!!)

                })



            }
        }
    }
}