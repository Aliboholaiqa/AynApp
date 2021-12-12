package com.twq.aynapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twq.aynapp.model.User
import com.twq.aynapp.network.Api
import com.twq.aynapp.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class UserRepository{

    var userService = Api.getInstance().create(UserService::class.java)

    // Login
    fun login(username:String,password:String):LiveData<User>{
        var mLiveData = MutableLiveData<User>()
        userService.getUserByUsernameAndPassword(username,password).enqueue(object :
            Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                var listOfUsers = response.body()
                if (listOfUsers?.isNotEmpty() == true){
                    mLiveData.postValue(listOfUsers[0])
                }else{
                    mLiveData.postValue(User("","","","","","","",""))
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Doc Snippet", "Failed to get login")
            }

        })
        return mLiveData
    }

    // Registration
    fun register (username: String,email: String, password: String):LiveData<User>{
        val user = User("","",email,"","","",password,username)
        val mLiveData = MutableLiveData<User>()
        userService.addUser(user).enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    var newUser = response.body()
                    mLiveData.postValue(newUser!!)
                    Log.d("Doc Snippet", "New user added")
                }else{
                    mLiveData.postValue(User("","","","", "","","",""))
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Doc Snippet", "Failed to add a user")
            }
        })
        return mLiveData
    }
}