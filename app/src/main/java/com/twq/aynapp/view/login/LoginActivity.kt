package com.twq.aynapp.view.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityLoginBinding
import com.twq.aynapp.utility.SharedPreferenceHelper
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.registration.RegistrationActivity

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val vm : LoginViewModel by viewModels()
        //Login using firebase
        binding.buttonLogin.setOnClickListener {
            vm.login(
                binding.editTextEmailLogin.text.toString(),
                binding.editTextPasswordLogin.text.toString()
            ).observe(this, {
                if (it) {
                    Log.w(TAG, "Login:success")
                    val intent = Intent(this, HomeActivity::class.java)
                    SharedPreferenceHelper.saveToken(this,auth.currentUser!!.uid)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "Login:failure")
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        //Go to registration page
        binding.buttonRegistration.setOnClickListener {
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }
        //Login by google
        binding.buttonByGoogle.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}