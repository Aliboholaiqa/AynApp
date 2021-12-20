package com.twq.aynapp.repository

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.twq.aynapp.model.User
import java.io.File
import java.util.*

class FirebaseRepository{

    lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var dbStorage : FirebaseStorage

    fun profileData(username:String, bio: String,avatar:String,header:String): LiveData<User>{
        val liveData = MutableLiveData<User>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .addSnapshotListener { user, error ->
                if(user !=null){
                    liveData.postValue(User(avatar,
                        user.getString("bio").toString(), "","",header,"" ,"",user.getString("username").toString()
                    ))
                }
            }
        return liveData
    }

    fun uploadImageToFirebase(fileUri: Uri) : LiveData<String> {
        dbStorage = Firebase.storage
        db = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        val livedata = MutableLiveData<String>()
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
                                Log.d(ContentValues.TAG, "Profile updated successfully")
                            }.addOnFailureListener {
                                Log.d(ContentValues.TAG, "Update error")
                            }
                    }
                }
                ?.addOnFailureListener{ e ->
                    print(e.message)
                }
        }
        return livedata
    }

    fun getUserInfo(username: String,bio: String,avatar:String): LiveData<User>{
        dbStorage = Firebase.storage
        val liveData = MutableLiveData<User>()
        db.collection("user").document(auth.currentUser?.uid.toString())
        .addSnapshotListener { user, error ->
            if(user !=null){
//                username.text = user.getString("username")
//                bio.text = user.getString("bio")

                val fileName = user.getString("avatar")
                val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
                val localFile = File.createTempFile("tempImg","jpg")
                refStorage.getFile(localFile).addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    //avatar.setImageBitmap(bitmap)
                }.addOnFailureListener{e->
                    Log.d("Doc","Failed to get an image")
                }
            }
        }
        return liveData
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

}