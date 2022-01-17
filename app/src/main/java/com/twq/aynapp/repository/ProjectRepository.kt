package com.twq.aynapp.repository

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.twq.aynapp.model.Project

class ProjectRepository {
    //var projectService = Api.getInstance().create(ProjectService::class.java)
    val auth = Firebase.auth
    val db = Firebase.firestore
    val dbStorage = Firebase.storage

    fun addProject(projectTitle: String,description: String,image: String,date: String): LiveData<Project> {
        val liveData = MutableLiveData<Project>()
        val project = hashMapOf(
            "date" to date,
            "title" to projectTitle,
            "description" to description,
            "image" to image,
            "userId" to auth.currentUser?.uid.toString()
        )
        db.collection("user").document(auth.currentUser?.uid.toString())
            .collection("project").add(project).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("Doc","Added project successfully")
                }else{
                    Log.d("Doc","Failed to add project")
                }
            }.addOnFailureListener {
                Log.d("Doc","Failed to add project")
            }
        return liveData
    }

    fun saveProject(projectTitle: String,description: String,image: String,date: String): LiveData<Project> {
        val liveData = MutableLiveData<Project>()
        val project = hashMapOf(
            "date" to date,
            "title" to projectTitle,
            "description" to description,
            "image" to image
        )
        db.collection("user").document(auth.currentUser?.uid.toString())
            .collection("saved").add(project).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("Doc","Saved project successfully")
                }else{
                    Log.d("Doc","Failed to save project")
                }
            }.addOnFailureListener {
                Log.d("Doc","Failure to save project")
            }
        return liveData
    }

    fun getSavedProjects(): MutableLiveData<List<Project>> {
        val mLiveData = MutableLiveData<List<Project>>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .collection("saved").get()
            .addOnCompleteListener { project ->
                if (project.isSuccessful) {
                    val list = mutableListOf<Project>()
                    for (document in project.result!!) {
                        list.add(
                            Project(
                                document.getString("date")!!,
                                document.getString("description")!!,
                                document.id,
                                document.getString("image")!!,
                                document.getString("title")!!,
                                document.getString("userId")!!
                            )
                        )
                    }
                    mLiveData.postValue(list)
                    Log.d("Get user projects: ", list.toString())
                }
            }
        // set the id as Project.id
        // to Project(project.id, date,..,..,..,..)
        // data[position].productid.toString
        return mLiveData
    }

    fun getUserProject(id:String): MutableLiveData<List<Project>> {
        val mLiveData = MutableLiveData<List<Project>>()
        db.collection("user").document(id)
            .collection("project").get()
            .addOnCompleteListener { project ->
                if (project.isSuccessful) {
                    val list = mutableListOf<Project>()
                    for (document in project.result!!) {
                        list.add(
                            Project(
                                document.getString("date")!!,
                                document.getString("description")!!,
                                document.id,
                                document.getString("image")!!,
                                document.getString("title")!!,
                                document.getString("userId")!!
                            )
                        )
                    }
                    mLiveData.postValue(list)
                    Log.d("Get user projects: ", list.toString())
                }
            }
        return mLiveData
    }

    fun getAllProjects(): MutableLiveData<List<Project>> {
        val mLiveData = MutableLiveData<List<Project>>()
        val list = mutableListOf<Project>()
        db.collection("user").get().addOnCompleteListener { user ->
            if (user.isSuccessful){
                for (documentID in user.result!!){
                    Log.d("Doc","Document ID is ${documentID.id}")
                    db.collection("user").document(documentID.id)
                        .collection("project").get()
                        .addOnCompleteListener { project ->
                            if (project.isSuccessful) {
                                for (document in project.result!!) {
                                    list.add(
                                        Project(
                                            document.getString("date")!!,
                                            document.getString("description")!!,
                                            document.id,
                                            document.getString("image")!!,
                                            document.getString("title")!!,
                                            documentID.id
                                        )
                                    )
                                }

                            }
                        }
                    Thread{
                        Thread.sleep(1000)
                        mLiveData.postValue(list)
                        Log.d("Doc", list.toString())
                    }.start()

                }

            }
            }
        return mLiveData
    }

    fun editProject(id:String, title:String, description: String, date: String): LiveData<Project>{
        val mLiveData = MutableLiveData<Project>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .collection("project").document(id).update(mapOf(
                "title" to title,
                "description" to description,
                "date" to date

            ))
        return mLiveData
    }

    fun updateProjectImg(image: String, id: String): LiveData<String>{
        val livedata = MutableLiveData<String>()
        db.collection("user")
            .document(auth.currentUser?.uid.toString()).collection("project").document(id)
            .update(
                mapOf(
                    "image" to image
                )
            ).addOnSuccessListener {
                livedata.postValue(image)
                Log.d(ContentValues.TAG, "Profile updated successfully")
            }.addOnFailureListener {
                Log.d(ContentValues.TAG, "Update error")
            }
        return livedata
    }

    fun deleteProject(id:String): LiveData<Project>{
        val mLiveData = MutableLiveData<Project>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .collection("project").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG,"Successfully deleted") }
            .addOnFailureListener { Log.d(TAG,"Failed to delete ")
            }
        return mLiveData
    }
}