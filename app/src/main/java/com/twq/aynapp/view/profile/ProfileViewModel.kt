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

    fun addProject(title:String,description: String,image: String, creationDate: String): LiveData<Project>{
        return projects.addProject(title,description,image,creationDate)
    }

    fun editUserProfile(username:String, bio: String):LiveData<User>{
        return fb.editUserProfile(username, bio)
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
        return user.getUserData()
    }

    fun getUserProject(): LiveData<List<Project>>{
        return projects.getUserProject()
    }

    fun signout(){
        return user.signout()
    }
}