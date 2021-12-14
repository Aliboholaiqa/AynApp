package com.twq.aynapp.network

import com.twq.aynapp.model.Project
import retrofit2.Call
import retrofit2.http.GET

interface ProjectService {
    @GET("project")
    fun getAllProjects(): Call<List<Project>>
}