package com.twq.aynapp.network

import com.twq.aynapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("users")
    fun getUserByUsernameAndPassword(@Query("username")username:String,
                                     @Query("password")password:String): Call<List<User>>


    @POST("users")
    fun addUser(@Body user: User): Call<User>
}