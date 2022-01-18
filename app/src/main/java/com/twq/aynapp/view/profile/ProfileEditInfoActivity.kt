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
    var img: String? = null
    var header: String? = null
    val vm: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        dbStorage = Firebase.storage

        vm.getUserData().observe(this, {
            binding.editTextEditProfileName.setText(it.username)
            binding.editTextEditProfileBio.setText(it.bio)
            img = it.avatar
            header = it.header
            Picasso.get().load(it.avatar).into(binding.imageViewProfileEditAvatar)
            Picasso.get().load(it.header).into(binding.imageViewProfileEditHeader)
        })

        // Changing profile avatar
        binding.buttonChangeAvatarImage.setOnClickListener {
            selectImageFromGallery(1)
        }

        // Changing profile header
        binding.buttonHeaderChange.setOnClickListener {
            selectImageFromGallery(2)
        }

        // Progress dialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Updating user profile")

        // Edit profile
        binding.buttonEditProfile.setOnClickListener {
            progressDialog.show()
            vm.editUserProfile(
                binding.editTextEditProfileName.text.toString(),
                binding.editTextEditProfileBio.text.toString(), img!!,
                header!!
            ).observe(this, {
                binding.editTextEditProfileName.setText(it.username)
                binding.editTextEditProfileBio.setText(it.bio)
                Picasso.get().load(it.avatar).into(binding.imageViewProfileEditAvatar)
                Picasso.get().load(it.header).into(binding.imageViewProfileEditHeader)
            })
            progressDialog.dismiss()
            finish()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    //image
    private fun selectImageFromGallery(requestCode:Int) {
        ImagePicker.with(this)

            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024) //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start(requestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if (resultCode == Activity.RESULT_OK) {
            // Get the Uri of data
            if (requestCode == 1) { // avatar request code
                val file_uri = data?.data
                if (file_uri != null) {
                    binding.imageViewProfileEditAvatar.setImageURI(file_uri)
                    vm.uploadImageToFirebase(file_uri).observe(this, {
                        img = it
                    })
                }
            } else if (requestCode == 2){ // //cover request code
                val file_uri = data?.data
                if (file_uri != null) {
                    binding.imageViewProfileEditHeader.setImageURI(file_uri)
                    vm.uploadImageToFirebase(file_uri).observe(this, {
                        header = it
                    })
                }


            }
       // }
    }
}

