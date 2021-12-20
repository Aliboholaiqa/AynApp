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
        binding.buttonRegistrationRegister.setOnClickListener {
            val vm : RegistrationViewModel by viewModels()
            vm.register(binding.editTextNameRegister.text.toString(),
                binding.editTextEmailRegister.text.toString(),
                binding.editTextPasswordRegister.text.toString()).observe(this,{
                if(it){
                    startActivity(Intent(this, HomeActivity::class.java))
                }else{
                    Toast.makeText(this, "Unable to register", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.buttonLoginRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}
