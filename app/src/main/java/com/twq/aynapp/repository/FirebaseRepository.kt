package com.twq.aynapp.repository

import android.content.ContentValues
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
import com.twq.aynapp.databinding.ActivityProfileEditInfoBinding
import java.util.*

class FirebaseRepository{

    lateinit var auth: FirebaseAuth
    lateinit var db : FirebaseFirestore
    lateinit var dbStorage : FirebaseStorage

    //fun createAuth(){auth = Firebase.auth}
    //fun createDB(){db = FirebaseFirestore.getInstance()}

    // Saving an image to firebase storage
    fun setImageInStorage(imgUri: Uri): LiveData<String> {
        dbStorage = Firebase.storage
        val filename = UUID.randomUUID().toString()+".jpg"
        val liveDataImage = MutableLiveData<String>()
        val ref = dbStorage.reference.child(Firebase.auth.uid.toString()).child(filename)
        val uploadTask = ref.putFile(imgUri)
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful){
                Log.d(ContentValues.TAG,"Not able to upload image")
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful){
                val downloadUri = task.result
                Log.d(ContentValues.TAG,downloadUri.toString())
                liveDataImage.postValue(downloadUri.toString())
            }
        }.addOnFailureListener {
            Log.d(ContentValues.TAG,"Not able to upload image")
        }
        return liveDataImage
    }
}