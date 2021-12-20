package com.twq.aynapp.utility

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.view.login.LoginActivity

class CheckLoggedinState {
    var auth = Firebase.auth
    fun checkState(){
        val user = auth.currentUser
        if (user == null){

        }else{

        }
    }
}