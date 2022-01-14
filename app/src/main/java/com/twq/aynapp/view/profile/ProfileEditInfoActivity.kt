package com.twq.aynapp.view.profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import com.twq.aynapp.databinding.ActivityProfileEditInfoBinding
import java.util.*
import kotlin.math.asinh

class ProfileEditInfoActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityProfileEditInfoBinding
    lateinit var db: FirebaseFirestore
    lateinit var dbStorage: FirebaseStorage
    val vm: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        dbStorage = Firebase.storage

        vm.getUserData().observe(this,{
            binding.editTextEditProfileName.setText(it.username)
            binding.editTextEditProfileBio.setText(it.bio)
            Picasso.get().load(it.avatar).into(binding.imageViewProfileEditAvatar)
        })
        // Changing profile avatar
        binding.buttonChangeAvatarImage.setOnClickListener {
            selectImageFromGallery()
        }


        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Waiting for updating user profile")
        binding.buttonEditProfile.setOnClickListener {
            progressDialog.show()
            vm.editUserProfile(binding.editTextEditProfileName.text.toString(),
            binding.editTextEditProfileBio.text.toString()).observe(this,{
                binding.editTextEditProfileName.setText(it.username)
                binding.editTextEditProfileBio.setText(it.bio)
                progressDialog.dismiss()
                finish()
            })
        }

        binding.buttonCancel.setOnClickListener {
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
                binding.imageViewProfileEditAvatar.setImageURI(file_uri)

                vm.uploadImageToFirebase(file_uri).observe(this,{
                    vm.updateAvatar(it)
                })
            }
        }
    }
}
