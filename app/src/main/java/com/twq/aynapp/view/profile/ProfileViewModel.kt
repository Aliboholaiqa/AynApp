package com.twq.aynapp.view.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twq.aynapp.model.Project
import com.twq.aynapp.model.User
import com.twq.aynapp.repository.FirebaseRepository
import com.twq.aynapp.repository.ProjectRepository
import com.twq.aynapp.repository.UserRepository

class ProfileViewModel : ViewModel(){
    var projects = ProjectRepository()
    var user = UserRepository()
    var fb = FirebaseRepository()

    fun updateProfile(username: String,id:String,image:String,email:String,bio:String,header:String):LiveData<User>{
        return user.updateProfile(username, id, image, email, bio, header)
    }

    fun setImg(image: Uri): LiveData<String> {
        return fb.setImageInStorage(image)
    }

    fun getProjectData(): LiveData<List<Project>> {
        return projects.projects()
    }
}