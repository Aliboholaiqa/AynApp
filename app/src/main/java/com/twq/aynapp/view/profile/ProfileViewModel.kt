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

    fun editUserProfile(username:String, bio: String, image: String, header: String):LiveData<User>{
        return user.editUserProfile(username, bio, image, header)
    }

    fun editProject(id:String, title:String, description: String, date: String): LiveData<Project>{
        return projects.editProject(id, title, description, date)
    }

    fun uploadImageToFirebase(fileUri: Uri): LiveData<String> {
        return fb.uploadImageToFirebase(fileUri)
    }

    fun updateProjectImg(image: String, id: String): LiveData<String>{
        return projects.updateProjectImg(image, id)
    }

    fun getImageFromFirebase(imageName:String, image: ImageView): LiveData<String>{
        return fb.getImageFromFirebase(imageName, image)
    }

    fun getUserData(): LiveData<User>{
        return user.getUserData()
    }

    fun getUserByID(id: String): LiveData<User>{
        return user.getUserByID(id)
    }

    fun getUserProject(id: String): LiveData<List<Project>>{
        return projects.getUserProject(id)
    }

    fun deleteProject(id: String): LiveData<Project>{
        return projects.deleteProject(id)
    }

    fun signout(){
        return user.signout()
    }

}