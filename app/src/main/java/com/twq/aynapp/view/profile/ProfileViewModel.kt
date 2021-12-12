package com.twq.aynapp.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.model.Project
import com.twq.aynapp.repository.ProjectRepository

class ProfileViewModel : ViewModel(){
    var projects = ProjectRepository()
    fun getProjectData(): LiveData<List<Project>> {
        return projects.projects()
    }
}