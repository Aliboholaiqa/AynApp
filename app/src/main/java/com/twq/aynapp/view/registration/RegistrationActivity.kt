package com.twq.aynapp.view.registration

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.R
import com.twq.aynapp.databinding.ActivityRegistrationBinding
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.home.HomeFragment
import com.twq.aynapp.view.login.LoginActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var db: Firebase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val db = Firebase.firestore

        binding.buttonRegistrationRegister.setOnClickListener {
            auth.createUserWithEmailAndPassword(
            binding.editTextEmailRegister.text.toString(),
            binding.editTextPasswordRegister.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val user = hashMapOf(
                            "username" to binding.editTextNameRegister,
                            "email" to binding.editTextEmailRegister,
                            "password" to binding.editTextPasswordRegister
                        )
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        db.collection("user").document(auth.currentUser?.uid.toString())
                            .set(user)
                    }
                    else{
                        Toast.makeText(this, "Unable to sign-in", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "")
                }
        }

        binding.buttonLoginRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}