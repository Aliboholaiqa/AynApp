package com.twq.aynapp.view.profile

import android.app.Activity
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
                binding.buttonAddProject.setOnClickListener {
                    vm.uploadImageToFirebase(file_uri).observe(this, {
                        val c = Calendar.getInstance()
                        val year = c.get(Calendar.YEAR)
                        val month = c.get(Calendar.MONTH)
                        val day = c.get(Calendar.DAY_OF_MONTH)
                        val date = Date(year,month,day)
                        vm.addProject(
                            binding.editTextAddProjectTitle.text.toString(),
                            binding.editTextAddProjectDescription.text.toString(), it, date.toString()).observe(this,{
                                finish()
                        })
                    }
                    )
                }
            }
        }
    }
}