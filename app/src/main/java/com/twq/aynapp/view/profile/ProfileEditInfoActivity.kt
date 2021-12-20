package com.twq.aynapp.view.profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileEditInfoBinding
import com.twq.aynapp.model.User
import com.twq.aynapp.view.home.HomeFragment
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

class ProfileEditInfoActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityProfileEditInfoBinding
    lateinit var db: FirebaseFirestore
    lateinit var dbStorage: FirebaseStorage
    lateinit var user: User
    val vm: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        dbStorage = Firebase.storage

        // Changing profile avatar
        binding.buttonChangeImage.setOnClickListener {
            selectImageFromGallery()
        }

        binding.buttonEditProfile.setOnClickListener {
            db.collection("user")
                .document(auth.currentUser?.uid.toString())
                .update(
                    mapOf(
                        "username" to binding.editTextEditProfileName.text.toString(),
                        "bio" to binding.editTextEditProfileBio.text.toString(),
                    )
                ).addOnSuccessListener {
                    Log.d(TAG, "Profile updated successfully")
                    finish()
                }.addOnFailureListener {
                    Log.d(TAG, "Update error")
                }
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
                vm.uploadImageToFirebase(file_uri)
            }
        }
    }
}
