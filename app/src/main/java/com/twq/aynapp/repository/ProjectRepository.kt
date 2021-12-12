package com.twq.aynapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.twq.aynapp.model.Project
import com.twq.aynapp.network.Api
import com.twq.aynapp.network.ProjectService
import com.twq.aynapp.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepository {
    var projectService = Api.getInstance().create(ProjectService::class.java)

    //Getting all the projects of all the user
    fun projects(): MutableLiveData<List<Project>> {
        var mLiveData = MutableLiveData<List<Project>>()
        projectService.getAllProjects().enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                val list = response.body()
                mLiveData.postValue(list!!)
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                Log.d("Doc Snippet", "Failed to get user")

            }

        })
        return mLiveData
    }

    //Getting a single user project for profile page

}