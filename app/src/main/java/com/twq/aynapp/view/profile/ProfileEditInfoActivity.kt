package com.twq.aynapp.view.profile

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityProfileEditInfoBinding
import java.io.ByteArrayOutputStream
import java.util.*

class ProfileEditInfoActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityProfileEditInfoBinding
    lateinit var db : FirebaseFirestore
    lateinit var dbStorage : FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        dbStorage = Firebase.storage
        val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()


        binding.buttonChangeImage.setOnClickListener {
            val takePictureIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(takePictureIntent,2)
        }

        binding.buttonEditProfile.setOnClickListener {
            db.collection("user")
                .document(auth.currentUser?.uid.toString())
                .update(mapOf(
                    "username" to binding.editTextEditProfileName.text.toString(),
                    "image" to binding.imageViewProfileEditAvatar,
                    "bio" to binding.editTextEditProfileBio.text.toString()
                )).addOnSuccessListener {
                    Log.d(TAG, "Profile updated successfully")
                    startActivity(Intent(this, ProfileFragment::class.java))
                }.addOnFailureListener {
                    Log.d(TAG, "Update error")
                }
        }
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            binding.imageViewProfileEditAvatar.setImageURI(uri)
            val vm:ProfileViewModel by viewModels()
            vm.setImg(uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled $resultCode", Toast.LENGTH_SHORT).show()
        }
    }



//    ImagePicker.with(this)
//    .crop()	    			//Crop image(Optional), Check Customization for more option
//    .compress(50) //Final image size will be less than 1 MB(Optional)
//    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//    .start()
//}
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

}

//            val vm:ProfileViewModel by viewModels()
//            vm.updateProfile(username, id, image, email, bio, header).observe(this,{

//            })
//            vm.updateProfile(user.username,user.password,user.id,encodedImgString!!,user.email).observe(this,{
//            })

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