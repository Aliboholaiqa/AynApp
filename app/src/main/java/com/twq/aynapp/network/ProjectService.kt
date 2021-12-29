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

    @POST("users/{userId}/project")
    fun addProject(@Body project: Project): Call<Project>

}