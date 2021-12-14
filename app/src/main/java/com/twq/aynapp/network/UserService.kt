package com.twq.aynapp.network

import com.twq.aynapp.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @GET("users")
    fun getUserByUsernameAndPassword(@Query("username")username:String,
                                     @Query("password")password:String): Call<List<User>>


    @PUT("User/{id}")
    fun uploadImage(@Path("id")id:String?, @Body user: User): Call<User>

    @POST("users")
    fun addUser(@Body user: User): Call<User>
}