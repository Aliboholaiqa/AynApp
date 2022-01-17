package com.twq.aynapp.utility

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.login.LoginActivity

class CheckState (): ViewModel(){
    var auth = Firebase.auth
    fun checkState(userId: String): Boolean{
        val user = auth.currentUser?.uid.toString()
        if (user == userId){
           return true
        }else{
            return false
        }
    }
}