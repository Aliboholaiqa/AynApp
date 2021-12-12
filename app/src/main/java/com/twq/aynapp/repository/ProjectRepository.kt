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

    fun projects(): MutableLiveData<List<Project>> {
        var mLiveData = MutableLiveData<List<Project>>()
        projectService.getAllProjects().enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                var list = response.body()
                mLiveData.postValue(list!!)
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                Log.d("Doc Snippet", "Failed to get user")

            }

        })
        return mLiveData
    }

}