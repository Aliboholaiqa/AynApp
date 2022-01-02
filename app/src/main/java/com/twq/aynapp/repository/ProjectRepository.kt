package com.twq.aynapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.twq.aynapp.model.Project
import com.twq.aynapp.model.User
import com.twq.aynapp.network.Api
import com.twq.aynapp.network.ProjectService
import com.twq.aynapp.utility.SharedPreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProjectRepository {
    var projectService = Api.getInstance().create(ProjectService::class.java)
    val auth = Firebase.auth
    val db = Firebase.firestore
    val dbStorage = Firebase.storage

    //Getting all the projects of all the user
//    fun projects(id:String): MutableLiveData<List<Project>> {
//        var mLiveData = MutableLiveData<List<Project>>()
//        projectService.getAllProjects(id).enqueue(object : Callback<List<Project>> {
//            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
//                val list = response.body()
//                println(list)
//                mLiveData.postValue(list!!)
//            }
//
//            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
//                Log.d("Doc Snippet", "Failed to get project")
//            }
//        })
//        return mLiveData
//    }



    fun addProject(projectTitle: String,description: String,image: String,date: String): LiveData<Project> {
        val liveData = MutableLiveData<Project>()
        val project = hashMapOf(
            "date" to date,
            "title" to projectTitle,
            "description" to description,
            "image" to image
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

    fun getUserProject(): MutableLiveData<List<Project>> {
        val mLiveData = MutableLiveData<List<Project>>()
        db.collection("user").document(auth.currentUser?.uid.toString())
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
                                ""
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
                                            ""
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
}