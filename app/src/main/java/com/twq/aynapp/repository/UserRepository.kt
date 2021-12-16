package com.twq.aynapp.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.model.User
import com.twq.aynapp.network.Api
import com.twq.aynapp.network.UserService
import com.twq.aynapp.utility.SharedPreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserRepository{

    val auth = Firebase.auth
    val db = Firebase.firestore
    var userService = Api.getInstance().create(UserService::class.java)

    fun updateProfile(username: String,id:String,image:String,email:String,bio:String,header:String):MutableLiveData<User>{
        val put = User("",bio,email,"",header,"","",username)
        val mLiveData = MutableLiveData<User>()
        userService.uploadImage(put.id,put).enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val updatedPost = response.body()
                    mLiveData.postValue(updatedPost!!)
                    Log.d("Doc", "Updated $updatedPost")
                    Log.d("Doc", "Body ${response.body()}")
                    Log.d("Doc", "Message ${response.message()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                mLiveData.postValue(User("","","","", "","","",""))
                Log.d("Doc Snippet", "Failed to upload image")
            }
        })
        return mLiveData
    }

    // Registration
    fun register (username: String,email: String, password: String):LiveData<User>{
        val mLiveData = MutableLiveData<User>()
        auth.createUserWithEmailAndPassword(
            email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val user = hashMapOf(
                        "fb_id" to auth.currentUser?.uid.toString(),
                        "token" to auth.currentUser?.getIdToken(true).toString(),
                        "username" to username,
                        "email" to auth.currentUser?.email
                    )
                    db.collection("user").document(auth.currentUser?.uid.toString())
                        .set(user)

                    val userAPI = User("","",email,auth.currentUser?.uid.toString(),"",
                        "","",username)

                    userService.addUser(userAPI).enqueue(object : Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val newUser = response.body()
                                mLiveData.postValue(newUser!!)
//                                val id = userAPI.id
//                                //val sh = SharedPreferenceHelper.saveToken(,id)
                                Log.d("Doc Snippet", "New user added")
                            }else{
                                mLiveData.postValue(User("","","","", "","","",""))
                            }
                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("Doc Snippet", "Failed to add a user")
                        }
                    })
                }
                else{
                    Log.d(ContentValues.TAG,"Not able to register")
                }
            }.addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Failed to register")
                Log.d(ContentValues.TAG, "Failed to register")
            }

        return mLiveData
    }


    // Login
//    fun login(username:String,password:String):LiveData<User>{
//        var mLiveData = MutableLiveData<User>()
//        userService.getUserByUsernameAndPassword(username,password).enqueue(object :
//            Callback<List<User>> {
//            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                var listOfUsers = response.body()
//                if (listOfUsers?.isNotEmpty() == true){
//                    mLiveData.postValue(listOfUsers[0])
//                }else{
//                    mLiveData.postValue(User("","","","","","",""))
//                }
//            }
//            override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                Log.d("Doc Snippet", "Failed to get login")
//            }
//
//        })
//        return mLiveData
//    }






}