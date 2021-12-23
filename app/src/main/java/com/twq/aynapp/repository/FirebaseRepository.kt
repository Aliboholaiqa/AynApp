package com.twq.aynapp.repository

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.twq.aynapp.model.User
import java.io.File
import java.util.*

class FirebaseRepository{

    val auth = Firebase.auth
    val db = Firebase.firestore
    val dbStorage = Firebase.storage

    fun updateUserProfile(username:String, bio: String,avatar:String,header:String): LiveData<User>{
        val livedata = MutableLiveData<User>()
            db.collection("user")
                .document(auth.currentUser?.uid.toString())
                .update(
                    mapOf(
                        "username" to username,
                        "bio" to bio,
                    )
                ).addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Profile updated successfully")
                }.addOnFailureListener {
                    Log.d(ContentValues.TAG, "Update error")
                }
        return livedata
    }

    fun uploadImageToFirebase(fileUri: Uri) : LiveData<String> {
        val livedata = MutableLiveData<String>()
        if (fileUri != null) {
            val fileName = UUID.randomUUID().toString() +".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
            refStorage.putFile(fileUri)
                .addOnSuccessListener{ taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        val imageUrl = it.toString()
                        Log.d("Doc", fileName)
                        livedata.postValue(fileName)
                    }
                }
                ?.addOnFailureListener{ e ->
                    print(e.message)
                }
        }
        return livedata
    }

    fun updateAvatar(imageName: String): LiveData<String>{
        val livedata = MutableLiveData<String>()
        db.collection("user")
            .document(auth.currentUser?.uid.toString())
            .update(
                mapOf(
                    "avatar" to imageName
                )
            ).addOnSuccessListener {
                livedata.postValue(imageName)
                Log.d(ContentValues.TAG, "Profile updated successfully")
            }.addOnFailureListener {
                Log.d(ContentValues.TAG, "Update error")
            }
        return livedata
    }

    fun getUserData(): LiveData<User>{
        val liveData = MutableLiveData<User>()
        db.collection("user").document(auth.currentUser?.uid.toString())
        .addSnapshotListener { user, error ->
            if(user !=null) {
                liveData.postValue(
                    User(
                        user.getString("avatar").toString(), user.getString("bio").toString(),
                        "", "", user.getString("header").toString(), "", "",
                        user.getString("username").toString()
                    )
                )
            }
        }
        return liveData
    }

    fun getAvatarImageFromFirebase(imageName: String,image: ImageView): LiveData<String> {
        val liveData = MutableLiveData<String>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .addSnapshotListener { user, error ->
                if (user != null) {
                   // val fileName = user.getString("avatar")
                       val fileName = imageName
                    val refStorage =
                        FirebaseStorage.getInstance().reference.child("images/$fileName")
                    val localFile = File.createTempFile("tempImg", "jpg")
                    refStorage.getFile(localFile).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                        image.setImageBitmap(bitmap)
                        liveData.postValue(imageName)
                    }.addOnFailureListener { e ->
                        Log.d("Doc", "Failed to get an image")
                    }
                }
            }
        return liveData
    }


//    fun getHeaderImageFromFirebase(image: ImageView): LiveData<String> {
//        val liveData = MutableLiveData<String>()
//        db.collection("user").document(auth.currentUser?.uid.toString())
//            .addSnapshotListener { user, error ->
//                if (user != null) {
//                    val fileName = user.getString("header")
//                    val refStorage =
//                        FirebaseStorage.getInstance().reference.child("images/$fileName")
//                    val localFile = File.createTempFile("tempImg", "jpg")
//                    refStorage.getFile(localFile).addOnSuccessListener {
//                        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//                        image.setImageBitmap(bitmap)
//                        liveData.postValue(image.toString())
//                    }.addOnFailureListener { e ->
//                        Log.d("Doc", "Failed to get an image")
//                    }
//                }
//            }
//        return liveData
//    }


}

    // Saving an image to firebase storage
//    fun setImageInStorage(imgUri: Uri): LiveData<String> {
//        dbStorage = Firebase.storage
//        val filename = UUID.randomUUID().toString()+".jpg"
//        val liveDataImage = MutableLiveData<String>()
//        val ref = dbStorage.reference.child(Firebase.auth.uid.toString()).child(filename)
//        val uploadTask = ref.putFile(imgUri)
//        uploadTask.continueWithTask { task ->
//            if (!task.isSuccessful){
//                Log.d(ContentValues.TAG,"Not able to upload image")
//            }
//            ref.downloadUrl
//        }.addOnCompleteListener { task ->
//            if (task.isSuccessful){
//                val downloadUri = task.result
//                Log.d(ContentValues.TAG,downloadUri.toString())
//                liveDataImage.postValue(downloadUri.toString())
//            }
//        }.addOnFailureListener {
//            Log.d(ContentValues.TAG,"Not able to upload image")
//        }
//        return liveDataImage
//    }

