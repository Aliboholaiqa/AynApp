package com.twq.aynapp.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.twq.aynapp.R
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            var token = it.result.toString()
            println("////////////"+token)
        }


    }
}