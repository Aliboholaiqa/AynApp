package com.twq.aynapp.repository

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.model.User
import com.twq.aynapp.network.Api
import com.twq.aynapp.network.UserService
import com.twq.aynapp.utility.SharedPreferenceHelper
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.profile.ProfileEditInfoActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import androidx.core.content.ContextCompat.startActivity

import androidx.annotation.NonNull
import androidx.core.content.ContextCompat

import com.google.android.gms.tasks.OnCompleteListener
import com.twq.aynapp.model.Project


class UserRepository{

    val auth = Firebase.auth
    val db = Firebase.firestore
    var userService = Api.getInstance().create(UserService::class.java)

    // Login
    fun login (email: String,password: String):LiveData<User>{
        val mLiveData = MutableLiveData<User>()
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(ContentValues.TAG,"User is $user")
                    Log.d(ContentValues.TAG, "signIn:success")
                    mLiveData.postValue(User("","",email,"","",
                        "",password,""))
                } else {

                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signIn:failure", task.exception)
                }
            }

        return mLiveData
    }

    // Registration
    fun register (username: String,email: String, password: String):LiveData<User> {
        val mLiveData = MutableLiveData<User>()
        auth.createUserWithEmailAndPassword(
            email, password
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = hashMapOf(
                        "fb_id" to auth.currentUser?.uid.toString(),
                        "token" to auth.currentUser?.getIdToken(true).toString(),
                        "username" to username,
                        "email" to auth.currentUser?.email
                    )
                    db.collection("user").document(auth.currentUser?.uid.toString())
                        .set(user)

                    val userAPI = User(
                        "", "", email, auth.currentUser?.uid.toString(), "",
                        "", "", username
                    )

                    userService.addUser(userAPI).enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val newUser = response.body()
                                mLiveData.postValue(newUser!!)
                                Log.d("Doc Snippet", "New user added")
                            } else {
                                mLiveData.postValue(User("", "", "", "",
                                    "", "", "", ""))
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("Doc Snippet", "Failed to add a user")
                        }
                    })
                } else {
                    Log.d(ContentValues.TAG, "Not able to register")
                }
            }.addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Failed to register")
                Log.d(ContentValues.TAG, "Failed to register")
            }

        return mLiveData
    }

    // Edit user profile
    fun editUserProfile(username:String, bio: String, image:String, header: String): LiveData<User>{
        val livedata = MutableLiveData<User>()
        db.collection("user")
            .document(auth.currentUser?.uid.toString())
            .update(
                mapOf(
                    "username" to username,
                    "bio" to bio,
                    "avatar" to image,
                    "header" to header
                )
            ).addOnSuccessListener {
                Log.d(ContentValues.TAG, "Profile updated successfully")
            }.addOnFailureListener {
                Log.d(ContentValues.TAG, "Update error")
            }
        return livedata
    }

    // Get current user data
    fun getUserData(): LiveData<User>{
        val liveData = MutableLiveData<User>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .addSnapshotListener { user, error ->
                if(user !=null) {
                    liveData.postValue(
                        User(
                            user.getString("avatar").toString(),
                            user.getString("bio").toString(),
                            "", user.getString("fb_id").toString(),
                            user.getString("header").toString(),
                            "", "",
                            user.getString("username").toString()
                        )
                    )
                }
            }
        return liveData
    }

    // GetUserByID
    fun getUserByID(id: String): LiveData<User>{
        val liveData = MutableLiveData<User>()
        db.collection("user").document(id)
            .addSnapshotListener { user, error ->
                if(user !=null) {
                    liveData.postValue(
                        User(
                            user.getString("avatar").toString(),
                            user.getString("bio").toString(),
                            user.getString("email").toString(),
                            user.getString("fb_id").toString(),
                            user.getString("header").toString(),
                            "", "",
                            user.getString("username").toString()
                        )
                    )
                }
                Log.d("ID",liveData.toString())
            }
        return liveData
    }

    // Get all users
    fun getAllUsers(): LiveData<List<User>> {
        val liveData = MutableLiveData<List<User>>()
        db.collection("user").addSnapshotListener { user, error ->
            val list = mutableListOf<User>()
            if (user != null) {
                for (document in user)
                    list.add(
                        User
                            (document.getString("avatar")!!,
                            document.getString("bio")!!,
                            document.getString("email")!!,
                            document.getString("fb_id")!!,
                            document.getString("header")!!,
                            document.id,
                            "",
                            document.getString("username")!!
                        )
                    )
            }
            liveData.postValue(list)
        }
        return liveData
    }

    // Sign out
    fun signout(){
         Firebase.auth.signOut()
    }
}