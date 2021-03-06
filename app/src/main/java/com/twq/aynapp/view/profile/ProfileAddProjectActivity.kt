package com.twq.aynapp.view.profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileAddProjectBinding
import java.util.*

class ProfileAddProjectActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileAddProjectBinding
    val vm: ProfileViewModel by viewModels()
    var img = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButtonAddImage.setOnClickListener {
            selectImageFromGallery()
        }

        binding.buttonCancelAdding.setOnClickListener {
            finish()
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = ("$day/${month + 1}/$year")
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Waiting for the project to be added")
        binding.buttonAddProject.setOnClickListener {
            progressDialog.show()
            vm.addProject(
                binding.editTextAddProjectTitle.text.toString(),
                binding.editTextAddProjectDescription.text.toString(), img, date)
            progressDialog.dismiss()
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
                binding.imageViewAddProjectImage.setImageURI(file_uri)
                vm.uploadImageToFirebase(file_uri).observe(this, {
                    img = it
                })
            }
        }
    }
}
