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

//    fun updateProfile(username: String,id:String,image:String,email:String,bio:String,header:String):LiveData<User>{
//        return user.updateProfile(username, id, image, email, bio, header)
//    }
//
//    fun getProfileInfo(username:String, bio: String,avatar:String,header:String): LiveData<User>{
//        return fb.profileData(username, bio, avatar, header)
//    }

    fun uploadImageToFirebase(fileUri: Uri): LiveData<String> {
        return fb.uploadImageToFirebase(fileUri)
    }

    fun getAvatarImageFromFirebase(image: ImageView): LiveData<String>{
        return fb.getAvatarImageFromFirebase(image)
    }

    fun getUserData(username:String, bio: String,avatar:ImageView,header:String): LiveData<User>{
        return fb.getUserData(username, bio, avatar, header)
    }

    fun getProjectData(): LiveData<List<Project>> {
        return projects.projects()
    }
}