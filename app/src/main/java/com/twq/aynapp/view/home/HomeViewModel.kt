package com.twq.aynapp.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.model.Project
import com.twq.aynapp.repository.ProjectRepository

class HomeViewModel : ViewModel(){

    var projects = ProjectRepository()

    fun getAllProjects(): LiveData<List<Project>>{
        return projects.getAllProjects()
    }

    fun saveProject(projectTitle: String,description: String,image: String,date: String): LiveData<Project> {
        return projects.saveProject(projectTitle, description, image, date)
    }

    fun getSavedProjects(): LiveData<List<Project>>{
        return projects.getSavedProjects()
    }

}