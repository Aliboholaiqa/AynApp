package com.twq.aynapp.view.profile

import android.net.Uri
import android.widget.ImageView
import androidx.lifecycle.LiveData
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

    fun addProject(title:String,description: String): LiveData<Project>{
        return fb.addProject(title,description)
    }

//    fun addImage(imageName: String): LiveData<String>{
//        return fb.addImage(imageName)
//    }

    fun updateUserProfile(username:String, bio: String,avatar:String,header:String):LiveData<User>{
        return fb.updateUserProfile(username, bio, avatar, header)
    }

    fun uploadImageToFirebase(fileUri: Uri): LiveData<String> {
        return fb.uploadImageToFirebase(fileUri)
    }
    fun updateAvatar(imageName: String): LiveData<String>{
        return fb.updateAvatar(imageName)
    }

    fun getImageFromFirebase(imageName:String, image: ImageView): LiveData<String>{
        return fb.getImageFromFirebase(imageName, image)
    }

    fun getUserData(): LiveData<User>{
        return fb.getUserData()
    }

    fun getProjectData(): LiveData<List<Project>> {
        return projects.projects()
    }

    fun signout(){
        return user.signout()
    }
}