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
                uploadImageToFirebase(file_uri)
            }
        }
    }
    private fun uploadImageToFirebase(fileUri: Uri) {
        if (fileUri != null) {
            val fileName = UUID.randomUUID().toString() +".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
            refStorage.putFile(fileUri)
                .addOnSuccessListener{ taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            val imageUrl = it.toString()
                            Log.d("Doc", fileName)
                            db.collection("user")
                                .document(auth.currentUser?.uid.toString())
                                .update(
                                    mapOf(
                                        "avatar" to fileName
                                    )
                                ).addOnSuccessListener {
                                    Log.d(TAG, "Profile updated successfully")
                                }.addOnFailureListener {
                                    Log.d(TAG, "Update error")
                                }
                        }
                    }
                ?.addOnFailureListener{ e ->
                    print(e.message)
                }
        }
    }
//    fun getImageFromFirebase(){
//        val progressDialog = ProgressDialog(this)
//        progressDialog.setMessage("Fetching an image")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//        val fileName = UUID.randomUUID().toString() +".jpg"
//        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
//        val localFile = File.createTempFile("tempImg","jpg")
//        refStorage.getFile(localFile).addOnSuccessListener {
//            if (progressDialog.isShowing){
//                progressDialog.dismiss()
//            }
//            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//            binding.imageViewProfileEditAvatar.setImageBitmap(bitmap)
//        }.addOnFailureListener{e->
//            Log.d("Doc","Failed to get an image")
//        }
//    }
}


//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            val uri: Uri = data?.data!!
//            binding.imageViewProfileEditAvatar.setImageURI(uri)
//            val vm:ProfileViewModel by viewModels()
//            vm.setImg(uri).observe(this,{
//                user.avatar = it
//            })
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Task Cancelled $resultCode", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            val uri: Uri = data?.data!!
//            val logBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
//            val encodedImgString = encodeToBase64(logBitmap, Bitmap.CompressFormat.JPEG, 100)
//
//            binding.buttonEditProfile.setOnClickListener {
//
//                db.collection("user")
//                    .document(auth.currentUser?.uid.toString())
//                    .update(mapOf(
//                        "username" to binding.editTextEditProfileName.text.toString(),
//                        "image" to encodedImgString,
//                        "bio" to binding.editTextEditProfileBio.text.toString()
//                    )).addOnSuccessListener {
//                        Log.d(TAG, "Profile updated successfully")
//                        startActivity(Intent(this, ProfileFragment::class.java))
//                    }.addOnFailureListener {
//                        Log.d(TAG, "Update error")
//                    }
//            }
//
//            binding.imageViewProfileEditAvatar.setImageBitmap(logBitmap)
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Task Cancelled $resultCode", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat?, quality: Int): String? {
//        val byteArrayOS = ByteArrayOutputStream()
//        image.compress(compressFormat, quality, byteArrayOS)
//        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT)
//    }
//
//    fun decodeBase64(input: String?): Bitmap? {
//        val decodedBytes = Base64.decode(input, 0)
//        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//    }
//
//}


//    fun updateProfile(){
//        auth.currentUser?.let { user ->
//            val username = binding.editTextEditProfileName.text.toString()
//            var email = binding.editTextEditProfileEmail.text.toString()
//            val image = Uri.parse(R.drawable.ic_launcher_ayn_background.toString())
//            val profileUpdate = UserProfileChangeRequest.Builder()
//                .setDisplayName(username)
//                .setPhotoUri(image)
//                .build()
//            user.updateProfile(profileUpdate)
//        }

//            val user = hashMapOf(
//                "username" to binding.editTextEditProfileName.text.toString(),
//                "email" to binding.editTextEditProfileEmail.text.toString(),
//                "image" to binding.imageViewProfileEditAvatar,
//                "bio" to binding.editTextEditProfileBio.text.toString()
//            )
//            db.collection("user")
//                .document(auth.currentUser?.uid.toString())
//                .collection("profile")
//                .document()
//                .set(user).addOnSuccessListener {
//                    Log.d(TAG, "Profile updated successfully")
//                    startActivity(Intent(this, ProfileFragment::class.java))
//                }.addOnFailureListener {
//                    Log.d(TAG, "Update error")
//                }