package com.twq.aynapp.view.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.twq.aynapp.databinding.ActivityRegistrationBinding
import com.twq.aynapp.view.home.HomeActivity
import com.twq.aynapp.view.login.LoginActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val db = Firebase.firestore

        binding.buttonRegistrationRegister.setOnClickListener {
            val vm : RegistrationViewModel by viewModels()
            vm.register(binding.editTextNameRegister.text.toString(),
                binding.editTextEmailRegister.text.toString(),
                binding.editTextPasswordRegister.text.toString()).observe(this,{
                if(it){
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Toast.makeText(this, "Unable to register", Toast.LENGTH_SHORT).show()
                    println("Registration error")
                }
            })
        }

        binding.buttonLoginRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}


//            auth.createUserWithEmailAndPassword(
//            binding.editTextEmailRegister.text.toString(),
//            binding.editTextPasswordRegister.text.toString())
//                .addOnCompleteListener {
//                    if (it.isSuccessful){
//                        val user = hashMapOf(
//                            "fb_id" to auth.currentUser?.uid.toString(),
//                            "username" to binding.editTextNameRegister.text.toString(),
//                            "email" to auth.currentUser?.email
//                        //token
//                        // var u = user(id,token,username,email)
//                        //user rep.addUser(u).observe
//                        //id  = user.id
//                        //Sharedph.save(id)
//                        )
//                        db.collection("user").document(auth.currentUser?.uid.toString())
//                            .set(user)
//
//                        val token = auth.currentUser?.getIdToken(true).toString()
//                        val fb_id = auth.currentUser?.uid.toString()
//                        var userAPI = User("","",auth.currentUser?.email!!,
//                            "",fb_id,binding.editTextNameRegister.text.toString(),token)
//                        val vm : RegistrationViewModel by viewModels()
//                        vm.register()
//
//                        val intent = Intent(this, HomeActivity::class.java)
//                        startActivity(intent)
//                    }
//                    else{
//                        Toast.makeText(this, "Unable to register", Toast.LENGTH_SHORT).show()
//                    }
//                }.addOnFailureListener { e ->
//                    Toast.makeText(this, "Failed to register" , Toast.LENGTH_SHORT).show()
//                    Log.d(TAG, "Failed to register")
//                }