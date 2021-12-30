package com.twq.aynapp.network

import com.twq.aynapp.model.Project
import com.twq.aynapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectService {

//    @GET("users/{userId}/project")
//    fun getAllProjects(@Path("userId")userId :String): Call<List<Project>>

    // Get all projects of a user
//    @GET("users/{userId}/project")
//    fun getAllProjects(@Path("userId")userId :String): Call<List<Project>>

    @GET("users/{fb_id}")
    fun getFbId(@Path("fb_id")fb_id:String): Call<User>

    @GET("users/{id}")
    fun getId(@Path("id")id:String): Call<User>

    @POST("users/{userId}/project")
    fun addProject(@Body project: Project): Call<Project>

}